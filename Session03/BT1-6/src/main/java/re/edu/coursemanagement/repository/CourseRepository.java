package re.edu.coursemanagement.repository;

import org.springframework.stereotype.Repository;
import re.edu.coursemanagement.model.Course;
import re.edu.coursemanagement.model.StatusEnum;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    private final List<Course> courses;

    public CourseRepository() {
        courses=new ArrayList<Course>();
        courses.add(new Course(101, "Lập trình Java Spring Boot", StatusEnum.OPEN, 1));
        courses.add(new Course(102, "Thiết kế Game Thực tế ảo (VR)", StatusEnum.CLOSED, 1));
        courses.add(new Course(103, "Cấu trúc dữ liệu & Giải thuật", StatusEnum.OPEN, 2));
    }

    public List<Course> getAllCourse(){
        return courses;
    }
}
