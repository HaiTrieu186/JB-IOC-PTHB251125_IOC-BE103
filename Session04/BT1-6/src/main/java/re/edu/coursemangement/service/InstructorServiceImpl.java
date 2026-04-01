package re.edu.coursemangement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import re.edu.coursemangement.entity.Instructor;
import re.edu.coursemangement.entity.InstructorCreateRequest;
import re.edu.coursemangement.repository.InstructorRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements IInstructorService{
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
    public Instructor updateInstructor(InstructorCreateRequest req, Long id) {
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
