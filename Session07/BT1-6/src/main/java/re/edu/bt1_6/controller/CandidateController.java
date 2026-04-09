package re.edu.bt1_6.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.edu.bt1_6.dto.request.candidate.CandidateCreateDTO;
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
            @Valid @RequestBody CandidateCreateDTO candidateCreateDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            result.getFieldErrors().forEach(err -> {
                errors.put(err.getField(), err.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }

        return new ResponseEntity<>(candidateService.createCandidate(candidateCreateDTO), HttpStatus.OK);
    }

}
