package re.edu.bt1_6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.bt1_6.dto.request.candidate.CandidateCreateDTO;
import re.edu.bt1_6.dto.respone.candidate.CandidateRespone;
import re.edu.bt1_6.entity.Candidate;
import re.edu.bt1_6.mapper.CandidateMapper;
import re.edu.bt1_6.repository.ICanditateRepository;
import re.edu.bt1_6.service.ICandidateService;


@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements ICandidateService {
    private final ICanditateRepository candidateRepository;

    @Override
    public CandidateRespone createCandidate(CandidateCreateDTO request) {
        Candidate candidate = CandidateMapper.RequestToEntity(request);
        candidate= candidateRepository.save(candidate);
        return CandidateMapper.EntityToRespone(candidate);
    }
}
