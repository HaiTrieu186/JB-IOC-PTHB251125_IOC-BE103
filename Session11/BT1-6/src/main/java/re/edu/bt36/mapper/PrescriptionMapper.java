package re.edu.bt36.mapper;

import re.edu.bt36.dto.response.PrescriptionResponse;
import re.edu.bt36.entity.Prescription;

public class PrescriptionMapper {
    public static PrescriptionResponse mapToPrescriptionResponse(Prescription prescription) {
        if (prescription == null) {
            return null;
        }

        return PrescriptionResponse.builder()
                .id(prescription.getId())
                .medicationDetails(prescription.getMedicationDetails())
                .instructions(prescription.getInstructions())
                .prescribedDate(prescription.getPrescribedDate())
                // Trích xuất ID bệnh nhân
                .patientId(prescription.getPatient() != null ? prescription.getPatient().getId() : null)
                .build();
    }
}
