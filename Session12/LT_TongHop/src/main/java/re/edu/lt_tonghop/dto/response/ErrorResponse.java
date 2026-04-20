package re.edu.lt_tonghop.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)// Tạo Constructor nhưng chỉ Builder được dùng
public class ErrorResponse<T> {
    private T error;
    private String message;
    private int status;

    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();
}
