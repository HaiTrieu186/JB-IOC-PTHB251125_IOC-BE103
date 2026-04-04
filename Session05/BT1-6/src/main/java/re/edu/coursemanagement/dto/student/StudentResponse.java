package re.edu.coursemanagement.dto.student;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentResponse {
    private Long id;;
    private String name;
    private String email;
}
