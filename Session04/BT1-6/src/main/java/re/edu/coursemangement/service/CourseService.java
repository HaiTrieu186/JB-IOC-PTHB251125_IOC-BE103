package re.edu.coursemangement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.coursemangement.entity.Course;
import re.edu.coursemangement.entity.DTO.CourseCreateRequest;
import re.edu.coursemangement.entity.DTO.CourseUpdateRequest;
import re.edu.coursemangement.entity.Instructor;
import re.edu.coursemangement.repository.CourseRepository;
import re.edu.coursemangement.repository.InstructorRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;


    @Override
    public List<Course> findAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy khóa học tương ứng !")
        );
    }

    @Override
    public Course createCourse(CourseCreateRequest course) {
        Instructor instructor = instructorRepository.findById(course.getInstructorId()).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm tháy giáo viên tương ứng")
        );

        Course newCourse = new Course();
        newCourse.setTitle(course.getTitle());
        newCourse.setStatus(course.getStatus());
        newCourse.setInstructor(instructor);
        return courseRepository.save(newCourse);
    }

    @Override
    public Course updateCourse(CourseUpdateRequest course, Long id) {
        Course currentCourse= courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy khóa học tương ứng !")
        );

        Instructor instructor = instructorRepository.findById(course.getInstructorId()).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm tháy giáo viên tương ứng")
        );

        currentCourse.setTitle(course.getTitle());
        currentCourse.setStatus(course.getStatus());
        currentCourse.setInstructor(instructor);
        return courseRepository.save(currentCourse);
    }

    @Override
    public void deleteCourseById(Long id) {
        Course currentCourse= courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy khóa học tương ứng !")
        );

        courseRepository.deleteById(id);
    }
}
