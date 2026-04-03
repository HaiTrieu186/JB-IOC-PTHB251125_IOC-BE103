package re.edu.coursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.coursemanagement.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
