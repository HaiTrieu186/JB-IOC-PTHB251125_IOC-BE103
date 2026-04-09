package re.edu.bt1_6.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import re.edu.bt1_6.dto.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        BindingResult result= ex.getBindingResult();

        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(
                err -> {errors.put(err.getField(), err.getDefaultMessage());
            });

        return new ApiResponse<>("FAIL","Dữ liệu không hợp lệ",errors);
    }
}
