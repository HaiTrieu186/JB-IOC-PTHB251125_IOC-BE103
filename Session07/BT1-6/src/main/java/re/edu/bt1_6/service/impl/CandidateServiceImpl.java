package re.edu.bt1_6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.bt1_6.dto.request.candidate.CandidateCreateDTO;
import re.edu.bt1_6.dto.request.candidate.CandidateUpdateDTO;
import re.edu.bt1_6.dto.respone.candidate.CandidateRespone;
import re.edu.bt1_6.entity.Candidate;
import re.edu.bt1_6.exception.ResourceNotFoundException;
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

    @Override
    public CandidateRespone updateCandidate(CandidateUpdateDTO request, Long id) {
        Candidate candidate= candidateRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Lỗi: Không tìm thấy ứng viên với id:" + id)
        );

        candidate.setBio(request.getBio()!= null ? request.getBio(): candidate.getBio());
        candidate.setAge( request.getAge());
        candidate.setAddress(request.getAddress()!=null?request.getAddress(): candidate.getAddress());
        candidate.setFullName(request.getFullName()!=null?request.getFullName():candidate.getFullName());
        candidate.setEmail(request.getEmail()!=null?request.getEmail():candidate.getEmail());
        candidate.setYearsOfExperience(request.getYearsOfExperience());

        candidate = candidateRepository.save(candidate);
        return CandidateMapper.EntityToRespone(candidate);
    }
}
