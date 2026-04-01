package re.edu.coursemangement.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentEnrollmentCreateRequest {
    private Long studentId;
    private Long courseId;
}
