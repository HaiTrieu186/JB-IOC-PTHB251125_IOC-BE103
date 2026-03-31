package re.edu.coursemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.coursemanagement.model.ApiResponse;
import re.edu.coursemanagement.model.Course;
import re.edu.coursemanagement.service.ICourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final ICourseService courseService;

    @Autowired
    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<?> getCourses(){
        ApiResponse<List<Course>> respone= new ApiResponse<>();
        List<Course> list= courseService.getAllCourse();

        respone.setSuccess(true);
        respone.setMessage("Lấy danh sách sản phẩm thành công !");
        respone.setData(list);

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable int id){
        ApiResponse<Course> response= new ApiResponse<>();

        try {
            Course c= courseService.getCourseById(id);
            response.setData(c);
            response.setSuccess(true);
            response.setMessage("Tìm thấy course tương ứng !");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course){
        ApiResponse<Course> response= new ApiResponse<>();

        try {
            Course c= courseService.createCourse(course);
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
    public ResponseEntity<?> updateCourse (@PathVariable int id, @RequestBody Course course){
        ApiResponse<Course> response= new ApiResponse<>();

        if (course.getId() != id) {
            response.setSuccess(false);
            response.setMessage("Dữ liệu không hợp lệ: ID trên URL (" + id + ") không khớp với ID trong nội dung gửi lên (" + course.getId() + ")!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Course c= courseService.updateCourse(course, id);
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
    public ResponseEntity<?> deleteCourse(@PathVariable int id){
        ApiResponse<Course> response= new ApiResponse<>();

        try {
            Course c= courseService.deleteCourseById(id);
            response.setData(c);
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
