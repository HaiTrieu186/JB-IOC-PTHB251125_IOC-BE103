package re.edu.coursemangement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.coursemangement.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
