package re.edu.coursemanagement.dto.course;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import re.edu.coursemanagement.entity.CourseStatus;

@Getter
@Setter
@Builder
public class CourseResponse {
    private Long id;
    private String title;
    private CourseStatus status;
    private CourseInstructorResponse instructor;
}
