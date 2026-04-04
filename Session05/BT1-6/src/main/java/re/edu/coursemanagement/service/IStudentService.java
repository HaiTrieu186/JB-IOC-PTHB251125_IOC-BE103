package re.edu.coursemanagement.service;

import org.springframework.data.domain.Sort;
import re.edu.coursemanagement.dto.PageResponse;
import re.edu.coursemanagement.dto.student.StudentResponse;
import re.edu.coursemanagement.entity.Student;


import java.util.List;

public interface IStudentService {
    PageResponse<StudentResponse> findAllStudent(int page, int size, String sortBy, Sort.Direction direction, String keyword);
    StudentResponse findStudentById(Long id);
//    StudentResponse createCourse(StudentCreateRequest course);
//    StudentResponse updateCourse(StudentUpdateRequest course, Long id);
    void deleteStudentById(Long id);
}
