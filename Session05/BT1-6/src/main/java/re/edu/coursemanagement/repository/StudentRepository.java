package re.edu.coursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.coursemanagement.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
