package re.edu.lt15.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import re.edu.lt15.dto.response.ErrorResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<?> handleJwtException(JwtException ex) {
        ErrorResponse<?> response = new ErrorResponse<>(
                "FAIL", HttpStatus.UNAUTHORIZED.value(), "Refresh Token không hợp lệ hoặc đã hết hạn. Vui lòng đăng nhập lại!", null, LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(
            {DuplicateResourceException.class}
    )
    public ResponseEntity<?> handleDuplicateResourceException(DuplicateResourceException ex) {
        ErrorResponse<?> errorResponse = new ErrorResponse<>();

        errorResponse.setStatus("FAIL");
        errorResponse.setCode(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex) {
        ErrorResponse<?> response = new ErrorResponse<>(
                "FAIL", HttpStatus.UNAUTHORIZED.value(), "Sai tên đăng nhập hoặc mật khẩu", null, LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        ErrorResponse<?> response = new ErrorResponse<>(
                "FAIL", HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null, LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ErrorResponse<Map<String, String>> response = new ErrorResponse<>(
                "FAIL", HttpStatus.BAD_REQUEST.value(), "Dữ liệu đầu vào không hợp lệ", errors, LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(IOException ex) {

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatus("FAIL");
        errorResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage("Lỗi: "+ex.getMessage());
        errorResponse.setTimestamp(LocalDateTime.now());

        return new  ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        ErrorResponse<?> response = new ErrorResponse<>(
                "FAIL", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Lỗi hệ thống: " + ex.getMessage(), null, LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
