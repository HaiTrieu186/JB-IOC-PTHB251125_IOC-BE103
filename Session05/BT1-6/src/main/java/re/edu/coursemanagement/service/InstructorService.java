package re.edu.coursemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.coursemanagement.dto.instructor.InstructorCreateRequest;
import re.edu.coursemanagement.dto.instructor.InstructorUpdateRequest;
import re.edu.coursemanagement.entity.Instructor;
import re.edu.coursemanagement.repository.InstructorRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService implements IInstructorService{
    private final InstructorRepository instructorRepository;


    @Override
    public List<Instructor> findAllInstructor() {
        return  instructorRepository.findAll();
    }

    @Override
    public Instructor findInstructorById(Long id) {
        return instructorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy giáo viên tương ứng !")
        );
    }

    @Override
    public Instructor createInstructor(InstructorCreateRequest req) {
        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());

        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor updateInstructor(InstructorUpdateRequest req, Long id) {
        Instructor crrInstructor = instructorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy giáo viên tương ứng !")
        );

        crrInstructor.setName(req.getName());
        crrInstructor.setEmail(req.getEmail());
        return instructorRepository.save(crrInstructor);
    }


    @Override
    public void deleteInstructorById(Long id) {
        Instructor crrInstructor = instructorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy giáo viên tương ứng !")
        );
        instructorRepository.deleteById(id);
    }
}
