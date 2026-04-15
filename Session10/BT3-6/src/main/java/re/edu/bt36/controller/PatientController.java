package re.edu.bt36.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.bt36.dto.request.PrescriptionCreateDTO;
import re.edu.bt36.dto.response.PrescriptionResponse;
import re.edu.bt36.service.PatientService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class PatientController {
    private final PatientService patientService;

    // GET /api/v1/patients/{patientId}/prescriptions/{prescriptionId}
    @GetMapping("/{patientId}/prescriptions/{prescriptionId}")
    public ResponseEntity<?> getPrescription(
            @PathVariable Long patientId,
            @PathVariable Long prescriptionId) {

        PrescriptionResponse response = patientService.getPrescriptionById(patientId, prescriptionId);

        return ResponseEntity.ok(response);
    }


    // POST /api/v1/patients/{patientId}/prescriptions
    @PostMapping("/{patientId}/prescriptions")
    public ResponseEntity<PrescriptionResponse> createPrescription(
            @PathVariable Long patientId,
            @RequestBody PrescriptionCreateDTO dto) {

        PrescriptionResponse response = patientService.addPrescription(dto, patientId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
