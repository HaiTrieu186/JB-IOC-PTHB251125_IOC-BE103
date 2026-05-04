package re.edu.lt15.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.edu.lt15.config.jwt.JwtProvider;
import re.edu.lt15.config.security.UserPrincipal;
import re.edu.lt15.dto.request.LoginRequestDTO;
import re.edu.lt15.dto.request.RegisterRequestDTO;
import re.edu.lt15.dto.response.AuthResponseDTO;
import re.edu.lt15.dto.request.RefreshTokenRequestDTO;
import re.edu.lt15.dto.response.UserResponse;
import re.edu.lt15.entity.User;
import re.edu.lt15.exception.DuplicateResourceException;
import re.edu.lt15.mapper.UserMapper;
import re.edu.lt15.repository.UserRepository;
import re.edu.lt15.service.IAuthService;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService implements IAuthService {
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    public UserResponse register(RegisterRequestDTO dto) {

        // Check trùng
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Email này đã được đăng ký!");
        }
        if (userRepository.existsByPhone(dto.getPhone())) {
            throw new DuplicateResourceException("Số điện thoại này đã được đăng ký!");
        }

        //  Map sang Entity
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("ROLE_CUSTOMER");

        // Lưu
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO dto) {
        //1. Kiểm tra username và password (AuthenticationManager gọi đến UserDetailsService check )
        // nếu lỗi --> ném BadCredentialsException
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);


        //2. Nếu dúng lấy thông tin username
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        // 3. Tạo token
        String jwt = jwtProvider.generateToken(userPrincipal);
        String refreshToken = jwtProvider.generateRefreshToken(userPrincipal);

        // Set thông tin
        User user = userPrincipal.getUser();
        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        // 4. trả về
        return AuthResponseDTO.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthResponseDTO refreshToken(RefreshTokenRequestDTO request) {
        String requestRefreshToken = request.getRefreshToken();


        // 1. Lấy username từ Refresh Token
        String username = jwtProvider.extractUserName(requestRefreshToken);

        // 2. Tìm user trong DB
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        // 3. Kiểm tra với db
        if (!requestRefreshToken.equals(user.getRefreshToken())) {
            throw new RuntimeException("Refresh Token không hợp lệ hoặc đã bị thu hồi!");
        }

        // 4. Tạo token mới và refresh token mới
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        UserPrincipal userPrincipal = new UserPrincipal(user, authorities);

        String newAccessToken = jwtProvider.generateToken(userPrincipal);
        String newRefreshToken = jwtProvider.generateRefreshToken(userPrincipal); // mỗi refresh xài 1 lần.

        // 5. Lưu vào DB
        user.setRefreshToken(newRefreshToken);
        userRepository.save(user);

        // 6. trả về
        return AuthResponseDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
