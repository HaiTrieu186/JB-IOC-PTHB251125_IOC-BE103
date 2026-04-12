package re.edu.bt16.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepartmentResponse {
    private Long id;
    private String name;
    private String description;
}
