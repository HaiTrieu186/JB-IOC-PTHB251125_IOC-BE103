package re.edu.bt36.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.edu.bt36.dto.response.ApiResponse;
import re.edu.bt36.dto.response.AppointmentResponse;
import re.edu.bt36.dto.response.Meta;
import re.edu.bt36.service.DoctorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/{doctorId}/appointments")
    public ResponseEntity<?> getAppointmentsByDoctor(
            @PathVariable Long doctorId) {
        ApiResponse<List<AppointmentResponse>> apiResponse = new ApiResponse<>();
        List<AppointmentResponse> appointments = doctorService.findAllAppointmentByDoctor(doctorId);

        apiResponse.setStatus("Success");
        apiResponse.setData(appointments);
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setMeta(new Meta());

        return ResponseEntity.ok(apiResponse);
    }
}
