package re.edu.coursemangement.service;



import re.edu.coursemangement.entity.DTO.InstructorUpdateRequest;
import re.edu.coursemangement.entity.Instructor;
import re.edu.coursemangement.entity.DTO.InstructorCreateRequest;

import java.util.List;

public interface IInstructorService {
    List<Instructor> findAllInstructor();
    Instructor findInstructorById(Long id);
    Instructor createInstructor(InstructorCreateRequest req);
    Instructor updateInstructor(InstructorUpdateRequest req, Long id);
    void deleteInstructorById(Long id);
}
