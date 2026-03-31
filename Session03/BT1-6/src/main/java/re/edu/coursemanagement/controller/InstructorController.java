package re.edu.coursemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.coursemanagement.model.ApiRespone;
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
        ApiRespone<List<Instructor>> response = new ApiRespone<>();
        List<Instructor> list = instructorService.getAllInstructor();

        response.setData(list);
        response.setMessage("Lấy danh sách giáo viên thành công !");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInstructor(@PathVariable int id) {
        ApiRespone<Instructor> response = new ApiRespone<>();
        Instructor i = instructorService.getInstructorById(id);

        response.setData(i);
        if (i == null) {
            response.setSuccess(false);
            response.setMessage("Không tìm thấy giáo viên tương ứng !");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        response.setSuccess(true);
        response.setMessage("Tìm thấy giáo viên tương ứng !");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addInstructor(@RequestBody Instructor instructor) {
        ApiRespone<Instructor> response = new ApiRespone<>();
        Instructor i = instructorService.createInstructor(instructor);

        response.setData(i);
        if (i==null){
            response.setSuccess(false);
            response.setMessage("Đã có lỗi trong khi tạo mới giáo viên !");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

        response.setSuccess(true);
        response.setMessage("Tạo mới thành công !");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInstructor(@PathVariable int id, @RequestBody Instructor instructor) {
        ApiRespone<Instructor> response = new ApiRespone<>();
        Instructor i = instructorService.updateInstructor(instructor, id);

        response.setData(i);
        if (i == null) {
            response.setSuccess(false);
            response.setMessage("Đã có lỗi trong khi cập nhật giáo viên !");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setSuccess(true);
        response.setMessage("Cập nhật thành công !");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable int id) {
        ApiRespone<Instructor> response = new ApiRespone<>();
        Instructor i = instructorService.deleteInstructorById(id);

        if (i == null) {
            response.setSuccess(false);
            response.setMessage("Không tìm thấy giáo viên tương ứng!");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.setSuccess(true);
        response.setMessage("Xóa thành công !");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
