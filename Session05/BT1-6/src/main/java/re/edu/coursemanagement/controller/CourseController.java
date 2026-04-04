package re.edu.coursemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.coursemanagement.dto.PageResponse;
import re.edu.coursemanagement.dto.course.CourseCreateRequest;
import re.edu.coursemanagement.dto.course.CourseResponse;
import re.edu.coursemanagement.dto.course.CourseResponseV2;
import re.edu.coursemanagement.dto.course.CourseUpdateRequest;
import re.edu.coursemanagement.entity.ApiResponse;
import re.edu.coursemanagement.entity.Course;
import re.edu.coursemanagement.entity.CourseStatus;
import re.edu.coursemanagement.service.ICourseService;


@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final ICourseService courseService;

    @GetMapping
    public ResponseEntity<?> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Sort.Direction direction,
            @RequestParam(required = false) CourseStatus status,
            @RequestParam(required = false) String keyword
            ){
        ApiResponse<PageResponse<CourseResponseV2>> respone= new ApiResponse<>();
        PageResponse<CourseResponseV2> list= courseService.getPagedCoursesByStatus(page, size, sortBy, direction, keyword, status);

        respone.setSuccess(true);
        respone.setMessage("Lấy danh sách sản phẩm thành công !");
        respone.setData(list);

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id){
        ApiResponse<CourseResponse> response= new ApiResponse<>();

        try {
            CourseResponse c= courseService.findCourseById(id);
            response.setData(c);
            response.setSuccess(true);
            response.setMessage("Tìm thấy khóa học tương ứng !");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody CourseCreateRequest request){
        ApiResponse<Course> response= new ApiResponse<>();

        try {
            Course c= courseService.createCourse(request);
            response.setData(c);
            response.setSuccess(true);
            response.setMessage("Tạo mới thành công !");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage("Đã có lỗi trong khi tạo mới course !");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse (@PathVariable Long id, @RequestBody CourseUpdateRequest request){
        ApiResponse<Course> response= new ApiResponse<>();

        try {
            Course c= courseService.updateCourse(request, id);
            response.setData(c);
            response.setSuccess(true);
            response.setMessage("Cập nhật thành công !");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        ApiResponse<Course> response= new ApiResponse<>();

        try {
            courseService.deleteCourseById(id);
            response.setSuccess(true);
            response.setMessage("Xóa course thành công !");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



}
