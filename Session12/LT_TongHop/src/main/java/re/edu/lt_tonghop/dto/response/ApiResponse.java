package re.edu.lt_tonghop.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse <T>{
    private T data;
    private String message;
    private int status;

    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();
}
