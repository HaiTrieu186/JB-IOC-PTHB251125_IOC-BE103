package re.edu.coursemangement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.coursemangement.entity.ApiResponse;
import re.edu.coursemangement.entity.DTO.InstructorCreateRequest;
import re.edu.coursemangement.entity.DTO.InstructorUpdateRequest;
import re.edu.coursemangement.entity.Instructor;
import re.edu.coursemangement.service.IInstructorService;


import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class InstructorController {
    private final IInstructorService instructorService;

    @GetMapping
    public ResponseEntity<?> getInstructors() {
        ApiResponse<List<Instructor>> response = new ApiResponse<>();
        List<Instructor> list = instructorService.findAllInstructor();
        response.setData(list);
        response.setMessage("Lấy danh sách giáo viên thành công !");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInstructor(@PathVariable Long id) {
        ApiResponse<Instructor> response = new ApiResponse<>();

        try {
            Instructor i = instructorService.findInstructorById(id);
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
    public ResponseEntity<?> addInstructor(@RequestBody InstructorCreateRequest request) {
        ApiResponse<Instructor> response = new ApiResponse<>();

        try {
            Instructor i = instructorService.createInstructor(request);
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
    public ResponseEntity<?> updateInstructor(@PathVariable Long id, @RequestBody InstructorUpdateRequest request) {
        ApiResponse<Instructor> response = new ApiResponse<>();

        try {
            Instructor i = instructorService.updateInstructor(request, id);
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
    public ResponseEntity<?> deleteInstructor(@PathVariable Long id) {
        ApiResponse<Instructor> response = new ApiResponse<>();

        try {
            instructorService.deleteInstructorById(id);
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
