package re.edu.coursemangement.entity.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@Builder
public class CourseInstructorResponse {
    private Long id;
    private String name;
}
