package re.edu.lt_tonghop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import re.edu.lt_tonghop.dto.response.ErrorResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // Lỗi 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        log.error("Lỗi không tìm thấy dữ liệu: ", ex);

        ErrorResponse<String> response = ErrorResponse.<String>builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(ex.getMessage())
                .message("Không tìm thấy dữ liệu tương ứng.")
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Lỗi nghiệp vụ chung (Ví dụ: xuất quá số lượng tồn, gửi dữ liệu cấm...)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("Lỗi nghiệp vụ logic: ", ex);

        ErrorResponse<String> response = ErrorResponse.<String>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Yêu cầu không hợp lệ do vi phạm quy tắc nghiệp vụ.")
                .error(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Lỗi Validation
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handlerMethodNotArgumentException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });

        // Ghi log ERROR StackTrace
        log.error("Lỗi định dạng dữ liệu: {}", errors, e);

        ErrorResponse<Map<String, String>> response = ErrorResponse.<Map<String, String>>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Đã có lỗi định dạng dữ liệu!")
                .error(errors)
                .build();

        return  new  ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }

    // Lỗi Sai kiểu tham số url (PathVariable / RequestParam)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String detail = String.format("Tham số '%s' nhận giá trị '%s' không đúng kiểu dữ liệu!", ex.getName(), ex.getValue());
        log.warn(detail);

        return ResponseEntity.badRequest().body(
                ErrorResponse.<String>builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Sai định dạng tham số trên URL!")
                        .error(detail)
                        .build()
        );
    }

    // Lỗi Đọc JSON / Sai kiểu dữ liệu TRONG BODY
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        log.warn("Lỗi đọc JSON: {}", ex.getMessage());

        return ResponseEntity.badRequest().body(
                ErrorResponse.<String>builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Định dạng JSON không hợp lệ hoặc sai kiểu dữ liệu trong Body!")
                        .error(ex.getMessage())
                        .build()
        );
    }

    // Lỗi nhập xuất
    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(IOException ex) {
        log.error("Lỗi đọc/ghi dữ liệu (IO): ", ex);

        ErrorResponse<String> response = ErrorResponse.<String>builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(ex.getMessage())
                .message("Lỗi trong quá trình xử lý dữ liệu đầu vào hoặc đầu ra.")
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    // Lỗi chung chung
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex) {
        log.error("Lỗi hệ thống không xác định: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ErrorResponse.<String>builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Hệ thống gặp sự cố.")
                        .error(ex.getClass().getSimpleName() + ": " + ex.getMessage())
                        .build()
        );
    }

}
