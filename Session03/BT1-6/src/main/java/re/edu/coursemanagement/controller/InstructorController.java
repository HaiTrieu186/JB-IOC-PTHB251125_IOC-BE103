package re.edu.coursemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.coursemanagement.model.ApiResponse;
import re.edu.coursemanagement.model.Instructor;
import re.edu.coursemanagement.service.IInstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final IInstructorService instructorService;

    @Autowired
    public InstructorController(IInstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<?> getInstructors() {
        ApiResponse<List<Instructor>> response = new ApiResponse<>();
        List<Instructor> list = instructorService.getAllInstructor();
        response.setData(list);
        response.setMessage("Lấy danh sách giáo viên thành công !");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInstructor(@PathVariable int id) {
        ApiResponse<Instructor> response = new ApiResponse<>();

        try {
            Instructor i = instructorService.getInstructorById(id);
            response.setSuccess(true);
            response.setMessage("Tìm thấy giáo viên tương ứng !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<?> addInstructor(@RequestBody Instructor instructor) {
        ApiResponse<Instructor> response = new ApiResponse<>();

        try {
            Instructor i = instructorService.createInstructor(instructor);
            response.setSuccess(true);
            response.setMessage("Tạo mới thành công !");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInstructor(@PathVariable int id, @RequestBody Instructor instructor) {
        ApiResponse<Instructor> response = new ApiResponse<>();

        if (instructor.getId() != id) {
            response.setSuccess(false);
            response.setMessage("Dữ liệu không hợp lệ: ID trên URL (" + id + ") không khớp với ID trong nội dung gửi lên (" + instructor.getId() + ")!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Instructor i = instructorService.updateInstructor(instructor, id);
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
    public ResponseEntity<?> deleteInstructor(@PathVariable int id) {
        ApiResponse<Instructor> response = new ApiResponse<>();

        try {
            Instructor i = instructorService.deleteInstructorById(id);
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
