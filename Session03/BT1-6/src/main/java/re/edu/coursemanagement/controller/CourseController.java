package re.edu.coursemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.coursemanagement.model.ApiRespone;
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
        ApiRespone<List<Course>> respone= new ApiRespone<>();
        List<Course> list= courseService.getAllCourse();

        respone.setSuccess(true);
        respone.setMessage("Lấy danh sách sản phẩm thành công !");
        respone.setData(list);

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable int id){
        ApiRespone<Course> response= new ApiRespone<>();
        Course c= courseService.getCourseById(id);

        response.setData(c);
        if (c==null){
            response.setSuccess(false);
            response.setMessage("Không tìm thấy course tương ứng !");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        response.setSuccess(true);
        response.setMessage("Tìm thấy course tương ứng !");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@RequestBody Course course){
        ApiRespone<Course> response= new ApiRespone<>();
        Course c= courseService.createCourse(course);

        response.setData(c);
        if (c==null){
            response.setSuccess(false);
            response.setMessage("Đã có lỗi trong khi tạo mới course !");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setSuccess(true);
        response.setMessage("Tạo mới thành công !");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse (@PathVariable int id, @RequestBody Course course){
        ApiRespone<Course> response= new ApiRespone<>();
        Course c= courseService.updateCourse(course, id);

        response.setData(c);
        if (c==null){
            response.setSuccess(false);
            response.setMessage("Đã có lỗi trong khi cập nhật !");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setSuccess(true);
        response.setMessage("Cập nhật thành công !");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id){
        ApiRespone<Course> response= new ApiRespone<>();
        Course c= courseService.deleteCourseById(id);

        response.setData(c);
        if (c==null){
            response.setSuccess(false);
            response.setMessage("Không tìm thấy course tương ứng !");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setSuccess(true);
        response.setMessage("Xóa course thành công !");
        return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
    }



}
