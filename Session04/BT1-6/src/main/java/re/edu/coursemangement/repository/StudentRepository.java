package re.edu.coursemangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.coursemangement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
