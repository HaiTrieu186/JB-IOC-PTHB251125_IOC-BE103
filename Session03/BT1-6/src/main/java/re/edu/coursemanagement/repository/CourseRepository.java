package re.edu.coursemanagement.repository;

import org.springframework.stereotype.Repository;
import re.edu.coursemanagement.model.Course;
import re.edu.coursemanagement.model.StatusEnum;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository implements IRepository<Course> {
    private final List<Course> courses;

    public CourseRepository() {
        courses = new ArrayList<Course>();
        courses.add(new Course(101, "Lập trình Java Spring Boot", StatusEnum.OPEN, 1));
        courses.add(new Course(102, "Thiết kế Game Thực tế ảo (VR)", StatusEnum.CLOSED, 1));
        courses.add(new Course(103, "Cấu trúc dữ liệu & Giải thuật", StatusEnum.OPEN, 2));
    }

    @Override
    public List<Course> findAll() {
        return courses;
    }

    @Override
    public Course findById(int id) {
        return courses.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Course create(Course course) {
        courses.add(course);
        return course;
    }

    @Override
    public Course update(int id, Course course) {
        Course c = findById(id);
        courses.set(courses.indexOf(c), course);
        return course;
    }

    @Override
    public Course deleteById(int id) {
        Course c = findById(id);
        courses.remove(c);
        return c;
    }


}
