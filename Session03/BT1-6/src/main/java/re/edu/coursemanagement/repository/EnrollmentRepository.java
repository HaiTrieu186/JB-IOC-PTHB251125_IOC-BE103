package re.edu.coursemanagement.repository;

import org.springframework.stereotype.Repository;
import re.edu.coursemanagement.model.Enrollment;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository {
    private final List<Enrollment> enrollments;

    public EnrollmentRepository() {
        enrollments =new ArrayList<Enrollment>();
        enrollments.add(new Enrollment(1, "Nguyễn Tuấn", 101));
        enrollments.add(new Enrollment(2, "Lê Minh", 101));
        enrollments.add(new Enrollment(3, "Hoàng Lan", 103));
        enrollments.add(new Enrollment(4, "Phạm Cường", 103));

    }

    public List<Enrollment> getAllEnrollment(){
        return enrollments;
    }
}
