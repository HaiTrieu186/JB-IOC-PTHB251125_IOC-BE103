package re.edu.bt16.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CandidateResponse {
    private Long id;
    private String name;
    private String email;
    private String cvUrl;
}
