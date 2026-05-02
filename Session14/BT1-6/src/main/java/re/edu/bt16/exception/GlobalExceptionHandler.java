package re.edu.bt16.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import re.edu.bt16.dto.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //Bắt lỗi sai Username/Password từ Manager ném ra
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex) {
        ApiResponse<?> response = new ApiResponse<>("Sai tên đăng nhập hoặc mật khẩu", 401, null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        ApiResponse<?> response = new ApiResponse<>(ex.getMessage(), 400, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        ApiResponse<?> response = new ApiResponse<>("Lỗi hệ thống: " + ex.getMessage(), 500, null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
