package re.edu.coursemanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.coursemanagement.model.ApiRespone;
import re.edu.coursemanagement.model.Enrollment;
import re.edu.coursemanagement.service.IEnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private  final IEnrollmentService enrollmentService;

    public EnrollmentController(IEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<?> getEnrollments() {
        ApiRespone<List<Enrollment>> response= new ApiRespone<>();
        List<Enrollment> list = enrollmentService.getAllEnrollment();

        response.setSuccess(true);
        response.setMessage("Lấy danh sách đăng ký thành công");
        response.setData(list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnrollment(@PathVariable int id) {
        ApiRespone<Enrollment> response= new ApiRespone<>();
        Enrollment e = enrollmentService.getEnrollmentById(id);

        response.setData(e);
        if (e == null) {
            response.setSuccess(false);
            response.setMessage("Không tìm thấy đăng ký tương ứng !");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        response.setSuccess(true);
        response.setMessage("Tìm thấy đăng ký tương ứng !");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addEnrollment(@RequestBody Enrollment enrollment) {
        ApiRespone<Enrollment> response= new ApiRespone<>();
        Enrollment e = enrollmentService.createEnrollment(enrollment);

        response.setData(e);
        if (e==null){
            response.setSuccess(false);
            response.setMessage("Đã có lỗi trong khi tạo mới đăng ký !");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        response.setSuccess(true);
        response.setMessage("Tạo mới thành công !");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEnrollment(@PathVariable int id, @RequestBody Enrollment enrollment) {
        ApiRespone<Enrollment> response= new ApiRespone<>();
        Enrollment e = enrollmentService.updateEnrollment(enrollment, id);

        response.setData(e);
        if (e==null){
            response.setSuccess(false);
            response.setMessage("Đã có lỗi trong khi cập nhật đăng ký !");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        response.setSuccess(true);
        response.setMessage("Cập nhật thành công !");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable int id) {
        ApiRespone<Enrollment> response= new ApiRespone<>();
        Enrollment e = enrollmentService.deleteEnrollmentById(id);

        if (e==null){
            response.setSuccess(false);
            response.setMessage("Không tìm thấy đăng ký tương ứng !");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setSuccess(true);
        response.setMessage("Xóa thành công !");
        return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
    }


}
