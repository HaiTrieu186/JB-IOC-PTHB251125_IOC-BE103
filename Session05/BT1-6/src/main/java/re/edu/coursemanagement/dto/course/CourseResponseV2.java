package re.edu.coursemanagement.dto.course;

import lombok.*;
import re.edu.coursemanagement.entity.CourseStatus;


@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CourseResponseV2 {
    private Long id;
    private String title;
    private CourseStatus status;
}
