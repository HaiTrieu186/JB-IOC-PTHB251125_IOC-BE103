package re.edu.bt16.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import re.edu.bt16.dto.ApiResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setStatus("FAILED");
        apiResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handlerMethodNotArgumentException(MethodArgumentNotValidException e){
        ApiResponse<Object> apiResponse = new ApiResponse<>();

        Map<String, String> errors = new HashMap<>(); // danh sách lỗi
        e.getFieldErrors().forEach(err->{
            errors.put(err.getField(), err.getDefaultMessage());
        });
        apiResponse.setStatus("FAILED");
        apiResponse.setMessage("Lỗi: đã có lỗi định dạng dữ liệu");
        apiResponse.setData(errors);

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);// 400
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(IOException ex) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setStatus("FAILED");
        apiResponse.setMessage("Lỗi: "+ex.getMessage());

        return new  ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
