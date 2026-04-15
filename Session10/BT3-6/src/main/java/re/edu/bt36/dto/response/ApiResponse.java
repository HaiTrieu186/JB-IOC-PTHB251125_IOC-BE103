package re.edu.bt36.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T>{
    private String status;
    private int code;
    private T data;
    private Meta meta;
}
