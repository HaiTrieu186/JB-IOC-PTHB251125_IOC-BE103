package re.edu.coursemangement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.coursemangement.entity.Course;
import re.edu.coursemangement.entity.DTO.CourseCreateRequest;
import re.edu.coursemangement.entity.DTO.CourseInstructorResponse;
import re.edu.coursemangement.entity.DTO.CourseResponse;
import re.edu.coursemangement.entity.DTO.CourseUpdateRequest;
import re.edu.coursemangement.entity.Instructor;
import re.edu.coursemangement.repository.CourseRepository;
import re.edu.coursemangement.repository.InstructorRepository;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;


    @Override
    public List<CourseResponse> findAllCourse() {
        return courseRepository.findAll().stream().map(
                this::mapCourseToDTO
        ).collect(Collectors.toList());
    }

    @Override
    public CourseResponse findCourseById(Long id) {
        Course course= courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy khóa học tương ứng !")
        );

        return mapCourseToDTO(course);
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
        Course currentCourse = courseRepository.findById(id).orElseThrow(
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
        Course currentCourse = courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Lỗi: Không tìm thấy khóa học tương ứng !")
        );

        courseRepository.deleteById(id);
    }

    private CourseResponse mapCourseToDTO(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .status(course.getStatus())
                .instructor(
                        CourseInstructorResponse.builder()
                                .name(course.getInstructor().getName())
                                .id(course.getInstructor().getId())
                                .build()
                )
                .build();
    }
}
