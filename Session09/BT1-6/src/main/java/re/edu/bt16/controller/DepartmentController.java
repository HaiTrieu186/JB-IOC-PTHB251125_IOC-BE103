package re.edu.bt16.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.edu.bt16.dto.ApiResponse;
import re.edu.bt16.dto.request.DepartmentDTO;
import re.edu.bt16.dto.response.DepartmentResponse;
import re.edu.bt16.service.IDepartmentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    private final IDepartmentService departmentService;

    @PostMapping
    public ResponseEntity<?> createDepartment(
            @Valid @RequestBody DepartmentDTO dto) {
        ApiResponse<DepartmentResponse> apiResponse = new ApiResponse<>();

        DepartmentResponse response= departmentService.createDepartment(dto);
        apiResponse.setStatus("SUCCESS");
        apiResponse.setMessage("Tạo mới thành công !");
        apiResponse.setData(response);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
