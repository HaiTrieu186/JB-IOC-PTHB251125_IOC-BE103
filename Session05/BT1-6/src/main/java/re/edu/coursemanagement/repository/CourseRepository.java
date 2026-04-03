package re.edu.coursemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import re.edu.coursemanagement.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Long> {
}
