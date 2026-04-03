package re.edu.coursemanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import re.edu.coursemanagement.dto.PageResponse;
import re.edu.coursemanagement.dto.course.CourseCreateRequest;
import re.edu.coursemanagement.dto.course.CourseInstructorResponse;
import re.edu.coursemanagement.dto.course.CourseResponse;
import re.edu.coursemanagement.dto.course.CourseUpdateRequest;
import re.edu.coursemanagement.entity.Course;
import re.edu.coursemanagement.entity.Instructor;
import re.edu.coursemanagement.repository.CourseRepository;
import re.edu.coursemanagement.repository.InstructorRepository;


import java.util.List;
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
        Course course = courseRepository.findById(id).orElseThrow(
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
    public PageResponse<CourseResponse> getPagedCourses(int page, int size, String sortBy, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page <0 ? 0: page, size, Sort.by(direction, sortBy));
        Page<Course> pageCourse = courseRepository.findAll(pageable);

        Page<CourseResponse> pageResponse = pageCourse.map(this::mapCourseToDTO);

        return mapPageToPageResponse(pageResponse);
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

    private PageResponse<CourseResponse> mapPageToPageResponse(Page<CourseResponse> page) {
        return PageResponse.<CourseResponse>builder()
                .items(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .isLast(page.isLast())
                .build();
    }
}
