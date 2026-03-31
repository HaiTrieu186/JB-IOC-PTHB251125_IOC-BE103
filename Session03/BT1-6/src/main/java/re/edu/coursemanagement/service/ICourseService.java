package re.edu.coursemanagement.service;

import re.edu.coursemanagement.model.Course;

import java.util.List;

public interface ICourseService {
    List<Course> getAllCourse();
    Course getCourseById(int id);
    Course createCourse(Course course);
    Course updateCourse(Course course, int id);
    Course deleteCourseById(int id);
}
