package re.edu.coursemanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import re.edu.coursemanagement.entity.Course;
import re.edu.coursemanagement.entity.CourseStatus;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


    @Query(value = "Select c from Course c where c.status= :status")
    public Page<Course> findAllByStatus(@Param("status") CourseStatus status, Pageable pageable);
}
