package re.edu.coursemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import re.edu.coursemanagement.dto.PageResponse;
import re.edu.coursemanagement.dto.student.StudentResponse;
import re.edu.coursemanagement.entity.ApiResponse;
import re.edu.coursemanagement.service.IStudentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final IStudentService studentService;

    @GetMapping
    public ResponseEntity<?> findAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Sort.Direction direction,
            @RequestParam(required = false) String keyword
    ) {
        ApiResponse<PageResponse<StudentResponse>> response = new ApiResponse<>();
        PageResponse<StudentResponse> pageResponse = studentService.findAllStudent(page, size, sortBy, direction, keyword);

        response.setSuccess(true);
        response.setMessage("Lấy danh sách học sinh thành công !");
        response.setData(pageResponse);

        return ResponseEntity.ok(response);
    }

}
