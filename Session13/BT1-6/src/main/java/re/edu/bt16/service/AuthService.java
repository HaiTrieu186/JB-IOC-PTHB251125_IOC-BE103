package re.edu.bt16.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import re.edu.bt16.dto.request.LoginRequest;
import re.edu.bt16.dto.request.RegisterRequest;
import re.edu.bt16.entity.User;
import re.edu.bt16.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


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

    public String login (LoginRequest dto, HttpSession  session){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //  Lưu vào Session
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return "Đăng nhập thành công! Role: " + authentication.getAuthorities();
    }
}
