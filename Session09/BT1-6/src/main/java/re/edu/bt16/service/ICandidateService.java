package re.edu.bt16.service;

import re.edu.bt16.dto.request.CandidateApplyDTO;
import re.edu.bt16.dto.response.CandidateResponse;
import re.edu.bt16.entity.Candidate;

import java.io.IOException;

public interface ICandidateService {
    CandidateResponse createCandidate(CandidateApplyDTO dto) throws IOException;
}
