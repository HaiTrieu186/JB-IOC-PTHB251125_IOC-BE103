package re.edu.coursemangement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.coursemangement.entity.Course;
import re.edu.coursemangement.entity.Student;
import re.edu.coursemangement.entity.StudentEnrollment;
import re.edu.coursemangement.repository.CourseRepository;
import re.edu.coursemangement.repository.StudentEnrollmentRepository;
import re.edu.coursemangement.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentEnrollmentService implements IStudentEnrollmentService{
    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public StudentEnrollment enrollStudent(Long studentId, Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy khóa học tương ứng !")
        );

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy học sinh tương ứng !")
        );

        StudentEnrollment studentEnrollment=new StudentEnrollment();
        studentEnrollment.setStudent(student);
        studentEnrollment.setCourse(course);

        return studentEnrollmentRepository.save(studentEnrollment);
    }
}
