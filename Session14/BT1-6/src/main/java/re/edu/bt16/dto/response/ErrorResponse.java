package re.edu.bt16.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse<T> {
    private String status;
    private int code;
    private String message;
    private T errors;
    private LocalDateTime timestamp;
}
