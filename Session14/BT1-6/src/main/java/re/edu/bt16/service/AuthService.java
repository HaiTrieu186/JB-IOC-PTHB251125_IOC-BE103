package re.edu.bt16.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import re.edu.bt16.config.jwt.JwtProvider;
import re.edu.bt16.dto.request.LoginRequest;
import re.edu.bt16.dto.request.RegisterRequest;
import re.edu.bt16.entity.User;
import re.edu.bt16.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    public User register(RegisterRequest dto){
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Lỗi: Tên đăng nhập đã tồn tại!");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        user.setRole("USER"); // mặc định

        user = userRepository.save(user);

        return user;
    }

    public Map<String, String> login (LoginRequest dto){
        //1. Kiểm tra username và password
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);

        //2. Nếu dúng lấy thông tin username
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 3. Tạo token
        String jwt = jwtProvider.generateToken(userDetails);

        // 4. Trả về dữ liệu
        Map<String, String> tokenData = new HashMap<>();
        tokenData.put("accessToken", jwt);
        tokenData.put("type", "Bearer");
        tokenData.put("username", userDetails.getUsername());

        return tokenData;
    }
}
