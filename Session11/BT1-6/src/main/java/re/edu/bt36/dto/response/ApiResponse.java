package re.edu.bt36.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse <T>{
    private String status;
    private int code;
    private T data;
    private Meta meta;
}
