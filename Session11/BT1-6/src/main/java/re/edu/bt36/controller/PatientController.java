package re.edu.bt36.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.bt36.dto.request.PatientCreateDTO;
import re.edu.bt36.dto.request.PrescriptionCreateDTO;
import re.edu.bt36.dto.response.ApiResponse;
import re.edu.bt36.dto.response.Meta;
import re.edu.bt36.dto.response.PatientResponse;
import re.edu.bt36.dto.response.PrescriptionResponse;
import re.edu.bt36.service.PatientService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
@Slf4j
public class PatientController {
    private final PatientService patientService;

    // GET /api/v1/patients/{patientId}/prescriptions/{prescriptionId}
    @GetMapping("/{patientId}/prescriptions/{prescriptionId}")
    public ResponseEntity<?> getPrescription(
            @PathVariable Long patientId,
            @PathVariable Long prescriptionId) {

        ApiResponse<PrescriptionResponse> apiResponse = new ApiResponse<>();
        PrescriptionResponse response = patientService.getPrescriptionById(patientId, prescriptionId);

        apiResponse.setStatus("Success");
        apiResponse.setData(response);
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMeta(new Meta());
        return ResponseEntity.ok(apiResponse);
    }


    // POST /api/v1/patients/{patientId}/prescriptions
    @PostMapping("/{patientId}/prescriptions")
    public ResponseEntity<?> createPrescription(
            @PathVariable Long patientId,
            @RequestBody PrescriptionCreateDTO dto) {

        ApiResponse<PrescriptionResponse> apiResponse = new ApiResponse<>();
        PrescriptionResponse response = patientService.addPrescription(dto, patientId);


        apiResponse.setStatus("Success");
        apiResponse.setData(response);
        apiResponse.setCode(HttpStatus.CREATED.value());
        apiResponse.setMeta(new Meta());

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    // POST /api/v1/patients
    @PostMapping
    public ResponseEntity<?> createPatient(@Valid @RequestBody PatientCreateDTO dto) {

        // lấy tên bệnh nhân
        log.info("Đang xử lý yêu cầu tạo bệnh nhân mới: {}", dto.getFullName());

        // Check tuổi quá 120 tuổi
        if (dto.getAge() != null && dto.getAge() > 120) {
            log.warn("Cảnh báo: Tuổi của bệnh nhân {} được gửi lên quá cao ({} tuổi)", dto.getFullName(), dto.getAge());
        }

        // Test tính năng tạo log cho error
        log.error("CẤP CỨU: Bệnh nhân {} đang trong tình trạng nguy kịch, tim ngừng đập!", dto.getFullName());


        PatientResponse response = patientService.createPatient(dto);

        ApiResponse<PatientResponse> apiResponse = new ApiResponse<>();
        apiResponse.setStatus("Success");
        apiResponse.setCode(201);
        apiResponse.setData(response);

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
