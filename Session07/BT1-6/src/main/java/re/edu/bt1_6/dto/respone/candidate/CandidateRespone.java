package re.edu.bt1_6.dto.respone.candidate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CandidateRespone {
    private Long id;
    private String fullName;
    private String email;
    private int age;
    private int yearsOfExperience;
}
