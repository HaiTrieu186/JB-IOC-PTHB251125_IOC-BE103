package re.edu.bt36.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PrescriptionCreateDTO {
    private String medicationDetails;
    private String instructions;
    private LocalDateTime prescribedDate;
}
