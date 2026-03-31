package re.edu.coursemanagement.repository;

import org.springframework.stereotype.Repository;
import re.edu.coursemanagement.model.Enrollment;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository implements IRepository<Enrollment> {
    private final List<Enrollment> enrollments;

    public EnrollmentRepository() {
        enrollments = new ArrayList<Enrollment>();
        enrollments.add(new Enrollment(1, "Nguyễn Tuấn", 101));
        enrollments.add(new Enrollment(2, "Lê Minh", 101));
        enrollments.add(new Enrollment(3, "Hoàng Lan", 103));
        enrollments.add(new Enrollment(4, "Phạm Cường", 103));

    }


    @Override
    public List<Enrollment> findAll() {
        return enrollments;
    }

    @Override
    public Enrollment findById(int id) {
        return enrollments.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Enrollment create(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollment;
    }

    @Override
    public Enrollment update(int id, Enrollment enrollment) {
        Enrollment en = findById(id);
        enrollments.set(enrollments.indexOf(en), enrollment);
        return enrollment;
    }

    @Override
    public Enrollment deleteById(int id) {
        Enrollment en = findById(id);
        enrollments.remove(en);
        return en;
    }
}
