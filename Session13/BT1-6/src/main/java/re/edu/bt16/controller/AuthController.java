package re.edu.bt16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
