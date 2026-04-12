package re.edu.bt16.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse <T>{
    private String message;
    private String status;
    private T data;
}
