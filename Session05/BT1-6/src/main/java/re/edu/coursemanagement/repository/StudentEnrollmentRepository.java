package re.edu.coursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.coursemanagement.entity.StudentEnrollment;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment,Long> {
}
