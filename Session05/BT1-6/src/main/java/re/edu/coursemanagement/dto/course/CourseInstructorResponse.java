package re.edu.coursemanagement.dto.course;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourseInstructorResponse {
    private Long id;
    private String name;
}
