package re.edu.bt36.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponse {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private Integer age;
}