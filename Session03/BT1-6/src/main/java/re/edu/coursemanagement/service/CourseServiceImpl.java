package re.edu.coursemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import re.edu.coursemanagement.model.Course;
import re.edu.coursemanagement.repository.CourseRepository;
import re.edu.coursemanagement.repository.IRepository;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    private final IRepository<Course> courseRepository;

    @Autowired
    public CourseServiceImpl(IRepository<Course> courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.create(course);
    }

    @Override
    public Course updateCourse(Course course, int id) {
        Course c = courseRepository.findById(id);

        if (c==null){
           return null;
        }
        return courseRepository.update(id, course);
    }

    @Override
    public Course deleteCourseById(int id) {
        Course c = courseRepository.findById(id);

        if (c==null){
            return null;
        }
        return courseRepository.deleteById(id);
    }


}
