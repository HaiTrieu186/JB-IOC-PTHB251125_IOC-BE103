package re.edu.bt36.mapper;

import re.edu.bt36.dto.response.AppointmentResponse;
import re.edu.bt36.entity.Appointment;

public class AppointmentMapper {
    public static AppointmentResponse mapToAppointmentResponse(Appointment appointment) {
        if (appointment == null) {
            return null;
        }

        return AppointmentResponse.builder()
                .id(appointment.getId())
                .appointmentDate(appointment.getAppointmentDate())
                .reason(appointment.getReason())
                .status(appointment.getStatus())
                // Trích xuất thông tin bệnh nhân (an toàn với null check)
                .patientId(appointment.getPatient() != null ? appointment.getPatient().getId() : null)
                .patientName(appointment.getPatient() != null ? appointment.getPatient().getFullName() : null)
                .build();
    }
}
