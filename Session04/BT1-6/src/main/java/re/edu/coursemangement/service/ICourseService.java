package re.edu.coursemangement.service;



import re.edu.coursemangement.entity.Course;
import re.edu.coursemangement.entity.DTO.CourseCreateRequest;
import re.edu.coursemangement.entity.DTO.CourseResponse;
import re.edu.coursemangement.entity.DTO.CourseUpdateRequest;

import java.util.List;

public interface ICourseService {
    List<CourseResponse> findAllCourse();
    CourseResponse findCourseById(Long id);
    Course createCourse(CourseCreateRequest course);
    Course updateCourse(CourseUpdateRequest course, Long id);
    void deleteCourseById(Long id);
}
