package re.edu.coursemanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.coursemanagement.model.ApiResponse;
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
        ApiResponse<List<Enrollment>> response= new ApiResponse<>();
        List<Enrollment> list = enrollmentService.getAllEnrollment();

        response.setSuccess(true);
        response.setMessage("Lấy danh sách đăng ký thành công");
        response.setData(list);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnrollment(@PathVariable int id) {
        ApiResponse<Enrollment> response= new ApiResponse<>();

        try {
            Enrollment e = enrollmentService.getEnrollmentById(id);
            response.setSuccess(true);
            response.setMessage("Tìm thấy đăng ký tương ứng !");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<?> addEnrollment(@RequestBody Enrollment enrollment) {
        ApiResponse<Enrollment> response= new ApiResponse<>();

        try {
            Enrollment e = enrollmentService.createEnrollment(enrollment);
            response.setSuccess(true);
            response.setMessage("Tạo mới thành công !");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEnrollment(@PathVariable int id, @RequestBody Enrollment enrollment) {
        ApiResponse<Enrollment> response= new ApiResponse<>();

        if (enrollment.getId() != id) {
            response.setSuccess(false);
            response.setMessage("Dữ liệu không hợp lệ: ID trên URL (" + id + ") không khớp với ID trong nội dung gửi lên (" + enrollment.getId() + ")!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Enrollment e = enrollmentService.updateEnrollment(enrollment, id);
            response.setSuccess(true);
            response.setMessage("Cập nhật thành công !");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable int id) {
        ApiResponse<Enrollment> response= new ApiResponse<>();

        try {
            Enrollment e = enrollmentService.deleteEnrollmentById(id);
            response.setSuccess(true);
            response.setMessage("Xóa thành công !");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }


}
