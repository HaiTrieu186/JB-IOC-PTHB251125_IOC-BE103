package re.edu.bt1_6.service;

import re.edu.bt1_6.dto.request.candidate.CandidateCreateDTO;
import re.edu.bt1_6.dto.request.candidate.CandidateUpdateDTO;
import re.edu.bt1_6.dto.respone.candidate.CandidateRespone;

public interface ICandidateService {
    CandidateRespone createCandidate(CandidateCreateDTO request);
    CandidateRespone updateCandidate(CandidateUpdateDTO request, Long id);
}
