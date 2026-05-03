package re.edu.lt15.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.edu.lt15.dto.request.LoginRequestDTO;
import re.edu.lt15.dto.request.RegisterRequestDTO;
import re.edu.lt15.dto.response.ApiResponse;
import re.edu.lt15.dto.response.AuthResponseDTO;
import re.edu.lt15.dto.response.RefreshTokenRequestDTO;
import re.edu.lt15.dto.response.UserResponse;
import re.edu.lt15.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody RegisterRequestDTO dto
    ) {
        UserResponse u = authService.register(dto);

        ApiResponse<UserResponse> response = new ApiResponse<>("SUCCESS", HttpStatus.CREATED.value(),"Đăng ký thành công",u);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @Valid @RequestBody LoginRequestDTO dto
    ) {
        AuthResponseDTO dto2 = authService.login(dto);

        ApiResponse<AuthResponseDTO> response = new ApiResponse<>("SUCCESS", HttpStatus.OK.value(), "Đăng nhập thành công",dto2);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(
            @Valid @RequestBody RefreshTokenRequestDTO request
    ) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }
}
