package re.edu.bt16.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.bt16.dto.ApiResponse;
import re.edu.bt16.dto.request.CandidateApplyDTO;
import re.edu.bt16.dto.response.CandidateResponse;
import re.edu.bt16.service.ICandidateService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/candidates")
public class CandidateController {
    private final ICandidateService candidateService;

    @PostMapping("/apply")
    public ResponseEntity<?> createCandidate(
            @Valid @ModelAttribute CandidateApplyDTO dto) throws IOException {
        ApiResponse<CandidateResponse> apiResponse = new ApiResponse<>();

        CandidateResponse cr= candidateService.createCandidate(dto);
        apiResponse.setData(cr);
        apiResponse.setStatus("SUCCESS");
        apiResponse.setMessage("Nộp thông tin ứng tuyển thành công");

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
