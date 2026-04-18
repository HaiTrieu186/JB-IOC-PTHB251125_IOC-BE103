package re.edu.bt36.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.bt36.dto.request.PatientCreateDTO;
import re.edu.bt36.dto.request.PrescriptionCreateDTO;
import re.edu.bt36.dto.response.PatientResponse;
import re.edu.bt36.dto.response.PrescriptionResponse;
import re.edu.bt36.entity.Patient;
import re.edu.bt36.entity.Prescription;
import re.edu.bt36.exception.ResourceNotFound;
import re.edu.bt36.mapper.PatientMapper;
import re.edu.bt36.mapper.PrescriptionMapper;
import re.edu.bt36.repository.IPatientRepository;
import re.edu.bt36.repository.IPrescriptionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final IPatientRepository patientRepository;
    private final IPrescriptionRepository prescriptionRepository;

    public List<PrescriptionResponse> findAllPrescriptionsByPatientId(Long patientId)
    {
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFound("Lỗi: không tìm thấy bệnh nhân với id: "+patientId)
        );

        List<Prescription> list= patient.getPrescriptions();

        return list.stream().map(PrescriptionMapper::mapToPrescriptionResponse).collect(Collectors.toList());
    }

    public PrescriptionResponse getPrescriptionById(Long patientId, Long prescriptionId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFound("Lỗi: không tìm thấy bệnh nhân với id: " + patientId)
        );

        // Tìm đơn thuốc theo ID
        Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(
                () -> new ResourceNotFound("Lỗi: không tìm thấy đơn thuốc với id: " + prescriptionId)
        );

        // Kiểm tra xem đơn thuốc này có đúng là của bệnh nhân này không
        if (!prescription.getPatient().getId().equals(patientId)) {
            throw new RuntimeException("Lỗi: Đơn thuốc này không thuộc về bệnh nhân id: " + patientId);
        }

        return PrescriptionMapper.mapToPrescriptionResponse(prescription);
    }

    public PrescriptionResponse addPrescription(PrescriptionCreateDTO dto, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new ResourceNotFound("Lỗi: không tìm thấy bệnh nhân với id: " + patientId)
        );

        Prescription newPrescription = new Prescription();
        newPrescription.setMedicationDetails(dto.getMedicationDetails());
        newPrescription.setInstructions(dto.getInstructions());
        newPrescription.setPrescribedDate(dto.getPrescribedDate());

        newPrescription.setPatient(patient);
        newPrescription= prescriptionRepository.save(newPrescription);

        return PrescriptionMapper.mapToPrescriptionResponse(newPrescription);
    }

    public PatientResponse createPatient(PatientCreateDTO dto) {
        // Khởi tạo Entity mới và gán dữ liệu từ DTO
        Patient newPatient = new Patient();
        newPatient.setFullName(dto.getFullName());
        newPatient.setPhoneNumber(dto.getPhoneNumber());
        newPatient.setAge(dto.getAge());

        // Lưu xuống Database
        Patient savedPatient = patientRepository.save(newPatient);

        // Chuyển đổi Entity sang Response DTO và trả về
        return PatientMapper.mapToPatientResponse(savedPatient);
    }

}
