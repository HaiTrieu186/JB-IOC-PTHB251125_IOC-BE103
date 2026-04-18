package re.edu.bt36.mapper;

import re.edu.bt36.dto.response.PatientResponse;
import re.edu.bt36.entity.Patient;

public class PatientMapper {
    public static PatientResponse mapToPatientResponse(Patient patient) {
        if (patient == null) {
            return null;
        }

        return PatientResponse.builder()
                .id(patient.getId())
                .fullName(patient.getFullName())
                .phoneNumber(patient.getPhoneNumber())
                .age(patient.getAge())
                .build();
    }
}
