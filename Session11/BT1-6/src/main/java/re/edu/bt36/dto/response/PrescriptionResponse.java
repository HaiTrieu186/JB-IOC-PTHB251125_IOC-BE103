package re.edu.bt36.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PrescriptionResponse {
    private Long id;
    private String medicationDetails;
    private String instructions;
    private LocalDateTime prescribedDate;
    private Long patientId;
}
