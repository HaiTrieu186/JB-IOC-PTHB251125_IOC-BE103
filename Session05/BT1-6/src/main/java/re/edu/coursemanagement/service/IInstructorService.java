package re.edu.coursemanagement.service;


import re.edu.coursemanagement.dto.instructor.InstructorCreateRequest;
import re.edu.coursemanagement.dto.instructor.InstructorUpdateRequest;
import re.edu.coursemanagement.entity.Instructor;

import java.util.List;

public interface IInstructorService {
    List<Instructor> findAllInstructor();
    Instructor findInstructorById(Long id);
    Instructor createInstructor(InstructorCreateRequest req);
    Instructor updateInstructor(InstructorUpdateRequest req, Long id);
    void deleteInstructorById(Long id);
}
