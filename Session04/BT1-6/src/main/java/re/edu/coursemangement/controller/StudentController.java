package re.edu.coursemangement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.coursemangement.entity.ApiResponse;
import re.edu.coursemangement.entity.DTO.StudentCreateRequest;
import re.edu.coursemangement.entity.DTO.StudentUpdateRequest;
import re.edu.coursemangement.entity.Student;
import re.edu.coursemangement.service.IStudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final IStudentService studentService;

    @GetMapping
    public ResponseEntity<?> getStudents(){
        ApiResponse<List<Student>> respone= new ApiResponse<>();
        List<Student> list= studentService.findAllStudents();

        respone.setSuccess(true);
        respone.setMessage("Lấy danh sách học sinh thành công !");
        respone.setData(list);

        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id){
        ApiResponse<Student> response= new ApiResponse<>();

        try {
            Student c= studentService.findStudentById(id);
            response.setData(c);
            response.setSuccess(true);
            response.setMessage("Tìm thấy học sinh tương ứng !");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody StudentCreateRequest request){
        ApiResponse<Student> response= new ApiResponse<>();

        try {
            Student s= studentService.createStudent(request);
            response.setData(s);
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
    public ResponseEntity<?> updateCourse (@PathVariable Long id, @RequestBody StudentUpdateRequest request){
        ApiResponse<Student> response= new ApiResponse<>();

        try {
            Student s= studentService.updateStudent(request,id);
            response.setData(s);
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
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        ApiResponse<Student> response= new ApiResponse<>();

        try {
           studentService.deleteStudentById(id);
            response.setSuccess(true);
            response.setMessage("Xóa học sinh thành công !");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



}
