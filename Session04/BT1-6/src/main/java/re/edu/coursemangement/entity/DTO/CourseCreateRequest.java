package re.edu.coursemangement.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.edu.coursemangement.entity.CourseStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseCreateRequest {
    private String title;
    private CourseStatus status;
    private Long instructorId;
}
