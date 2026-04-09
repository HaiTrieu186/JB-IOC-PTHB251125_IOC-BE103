package re.edu.bt1_6.mapper;

import re.edu.bt1_6.dto.request.candidate.CandidateCreateDTO;
import re.edu.bt1_6.dto.respone.candidate.CandidateRespone;
import re.edu.bt1_6.entity.Candidate;

public class CandidateMapper {
    public static CandidateRespone EntityToRespone(Candidate candidate) {
        return CandidateRespone.builder()
                .id(candidate.getId())
                .age(candidate.getAge())
                .email(candidate.getEmail())
                .fullName(candidate.getFullName())
                .yearsOfExperience(candidate.getYearsOfExperience())
                .build();
    };


    public static Candidate RequestToEntity(CandidateCreateDTO candidateCreateDTO) {
        Candidate c = new Candidate();

        c.setFullName(candidateCreateDTO.getFullName());
        c.setEmail(candidateCreateDTO.getEmail());
        c.setYearsOfExperience(candidateCreateDTO.getYearsOfExperience());
        c.setAge(candidateCreateDTO.getAge());

        return c;
    }


}
