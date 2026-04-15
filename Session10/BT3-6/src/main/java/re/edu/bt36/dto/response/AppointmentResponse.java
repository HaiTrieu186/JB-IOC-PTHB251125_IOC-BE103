package re.edu.bt36.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import re.edu.bt36.entity.AppointmentStatusEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDate;
    private String reason;
    private AppointmentStatusEnum status;
    private Long patientId;
    private String patientName;
}
