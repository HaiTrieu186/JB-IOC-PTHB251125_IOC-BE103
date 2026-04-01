package re.edu.coursemangement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.coursemangement.entity.DTO.StudentCreateRequest;
import re.edu.coursemangement.entity.DTO.StudentUpdateRequest;
import re.edu.coursemangement.entity.Student;
import re.edu.coursemangement.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{
    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy học sinh tương ứng !")
        );
    }

    @Override
    public Student createStudent(StudentCreateRequest request) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());

        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(StudentUpdateRequest request, Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy học sinh tương ứng !")
        );

        student.setName(request.getName());
        student.setEmail(request.getEmail());

        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy học sinh tương ứng !")
        );

        studentRepository.deleteById(id);
    }
}
