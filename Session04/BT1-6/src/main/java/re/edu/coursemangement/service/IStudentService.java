package re.edu.coursemangement.service;

import re.edu.coursemangement.entity.DTO.StudentCreateRequest;
import re.edu.coursemangement.entity.DTO.StudentUpdateRequest;
import re.edu.coursemangement.entity.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAllStudents();
    Student findStudentById(Long id);
    Student createStudent(StudentCreateRequest request);
    Student updateStudent(StudentUpdateRequest request, Long id);
    void deleteStudentById(Long id);
}
