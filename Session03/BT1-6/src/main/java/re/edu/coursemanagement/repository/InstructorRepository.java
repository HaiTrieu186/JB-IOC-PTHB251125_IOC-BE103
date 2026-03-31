package re.edu.coursemanagement.repository;

import org.springframework.stereotype.Repository;
import re.edu.coursemanagement.model.Enrollment;
import re.edu.coursemanagement.model.Instructor;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InstructorRepository implements IRepository<Instructor> {
    private final List<Instructor> instructors;

    public InstructorRepository() {
        instructors =new ArrayList<Instructor>();
        instructors.add(new Instructor(1, "Triều Phạm", "trieu.pham@ptithcm.edu.vn"));
        instructors.add(new Instructor(2, "Trần Thanh Bảo", "tranb@gmail.com"));

    }

    public List<Instructor> getAllInstructor(){
        return instructors;
    }

    @Override
    public List<Instructor> findAll() {
        return instructors;
    }

    @Override
    public Instructor findById(int id) {
        return instructors.stream().filter(i -> i.getId()==id).findFirst().orElse(null);
    }

    @Override
    public Instructor create(Instructor instructor) {
        instructors.add(instructor);
        return instructor;
    }

    @Override
    public Instructor update(int id, Instructor instructor) {
        Instructor i = findById(id);

        if (i!=null){
            instructors.set(instructors.indexOf(i),instructor);
            return instructor;
        }
        return null;
    }

    @Override
    public Instructor deleteById(int id) {
        Instructor i = findById(id);

        if (i!=null){
           instructors.remove(i);
           return i;
        }
        return null;
    }

}
