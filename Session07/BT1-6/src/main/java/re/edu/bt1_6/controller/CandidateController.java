package re.edu.bt1_6.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import re.edu.bt1_6.dto.request.candidate.CandidateCreateDTO;
import re.edu.bt1_6.dto.request.candidate.CandidateUpdateDTO;
import re.edu.bt1_6.dto.respone.candidate.CandidateRespone;
import re.edu.bt1_6.entity.Candidate;
import re.edu.bt1_6.service.ICandidateService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/candidates")
public class CandidateController {
    private final ICandidateService candidateService;

    @PostMapping
    public ResponseEntity<?> createCandidate(
            @Valid @RequestBody CandidateCreateDTO candidateCreateDTO
    ) {
        return new ResponseEntity<>(candidateService.createCandidate(candidateCreateDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateRespone> updateCandidate(
            @PathVariable Long id,
            @Valid @RequestBody CandidateUpdateDTO candidateUpdateDTO
    ) {
        return new ResponseEntity<>(candidateService.updateCandidate(candidateUpdateDTO, id), HttpStatus.OK);
    }

}
