package re.edu.lt15.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import re.edu.lt15.dto.request.UserRoleUpdateRequestDTO;
import re.edu.lt15.dto.response.ApiResponse;
import re.edu.lt15.dto.response.UserResponse;
import re.edu.lt15.service.IUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final IUserService userService;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()") // Bất kỳ ai có token đều được  (Customer, Staff, Admin)
    public ResponseEntity<?> getProfile() {
        UserResponse userResponse = userService.getProfile();

        ApiResponse<UserResponse> response = new ApiResponse<>(
                "SUCCESS",
                HttpStatus.OK.value(),
                "Lấy thông tin tài khoản thành công",
                userResponse
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/{id}/role")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateRole(
            @PathVariable("id") Long userId,
            @Valid @RequestBody UserRoleUpdateRequestDTO request
    ) {
        UserResponse updatedUser = userService.updateRole(userId, request);

        ApiResponse<UserResponse> response = new ApiResponse<>(
                "SUCCESS",
                HttpStatus.OK.value(),
                "Cập nhật quyền thành công",
                updatedUser
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
