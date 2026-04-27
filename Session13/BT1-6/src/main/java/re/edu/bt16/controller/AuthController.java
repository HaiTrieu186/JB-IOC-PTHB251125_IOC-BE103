package re.edu.bt16.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import re.edu.bt16.dto.request.LoginRequest;
import re.edu.bt16.dto.request.RegisterRequest;
import re.edu.bt16.entity.Employee;
import re.edu.bt16.entity.User;
import re.edu.bt16.service.AuthService;

import java.math.BigDecimal;

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
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest dto
            ){
        User u =  authService.register(dto);
        return ResponseEntity.ok(u);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest dto,
            HttpSession session
    ){
        try {
            String result = authService.login(dto, session);
            return ResponseEntity.ok(result);

        } catch (BadCredentialsException e) {
            // Gửi sai thông tin -> Trả về 401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("username or password incorrect");
        }
    }
}
