package re.edu.coursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.coursemanagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
