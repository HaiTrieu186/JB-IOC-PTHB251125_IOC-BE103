package re.edu.coursemanagement.service;





import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import re.edu.coursemanagement.dto.PageResponse;
import re.edu.coursemanagement.dto.course.CourseCreateRequest;
import re.edu.coursemanagement.dto.course.CourseResponse;
import re.edu.coursemanagement.dto.course.CourseUpdateRequest;
import re.edu.coursemanagement.entity.Course;

import java.util.List;

public interface ICourseService {
    List<CourseResponse> findAllCourse();
    CourseResponse findCourseById(Long id);
    Course createCourse(CourseCreateRequest course);
    Course updateCourse(CourseUpdateRequest course, Long id);
    PageResponse<CourseResponse> getPagedCourses(int page, int size, String sortBy, Sort.Direction direction);
    void deleteCourseById(Long id);
}
