package re.edu.bt16.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class ApiResponse <T>{
    private String message;
    private int status;
    private T data;
}
