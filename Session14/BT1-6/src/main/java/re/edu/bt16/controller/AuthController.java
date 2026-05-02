package re.edu.bt16.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import re.edu.bt16.dto.request.LoginRequest;
import re.edu.bt16.dto.request.RegisterRequest;
import re.edu.bt16.dto.response.ApiResponse;
import re.edu.bt16.entity.User;
import re.edu.bt16.service.AuthService;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/test")
    public ResponseEntity<?> getEmployees(){

        return ResponseEntity.ok("hello");
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest dto) {
        User u = authService.register(dto);

        ApiResponse<User> response = new ApiResponse<>("Đăng ký thành công", 200, u);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest dto) {
        Map<String, String> tokenData = authService.login(dto);

        ApiResponse<Map<String, String>> response = new ApiResponse<>("Đăng nhập thành công", 200, tokenData);
        return ResponseEntity.ok(response);
    }
}
