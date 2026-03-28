package re.edu.coursemanagement.repository;

import org.springframework.stereotype.Repository;
import re.edu.coursemanagement.model.Enrollment;
import re.edu.coursemanagement.model.Instructor;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorRepository {
    private final List<Instructor> instructors;

    public InstructorRepository() {
        instructors =new ArrayList<Instructor>();
        instructors.add(new Instructor(1, "Triều Phạm", "trieu.pham@ptithcm.edu.vn"));
        instructors.add(new Instructor(2, "Trần Thanh Bảo", "tranb@gmail.com"));

    }

    public List<Instructor> getAllInstructor(){
        return instructors;
    }
}
