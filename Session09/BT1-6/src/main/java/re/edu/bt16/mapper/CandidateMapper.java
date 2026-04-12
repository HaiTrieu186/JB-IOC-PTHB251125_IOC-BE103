package re.edu.bt16.mapper;

import re.edu.bt16.dto.response.CandidateResponse;
import re.edu.bt16.entity.Candidate;

public class CandidateMapper {
    public static CandidateResponse mapEntityToResponse(Candidate candidate) {
        return  CandidateResponse.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .email(candidate.getEmail())
                .cvUrl(candidate.getCvUrl())
                .build();
    }
}
