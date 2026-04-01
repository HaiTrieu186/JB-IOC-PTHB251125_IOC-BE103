package re.edu.coursemangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.coursemangement.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
