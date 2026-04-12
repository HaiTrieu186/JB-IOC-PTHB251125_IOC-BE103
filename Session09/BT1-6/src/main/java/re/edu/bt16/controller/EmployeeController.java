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
import re.edu.bt16.dto.request.EmployeeCreateDTO;
import re.edu.bt16.dto.response.EmployeeResponse;
import re.edu.bt16.entity.Employee;
import re.edu.bt16.service.IEmployeeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final IEmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeCreateDTO dto) {
        ApiResponse<EmployeeResponse> apiResponse = new ApiResponse<>();

        EmployeeResponse er = employeeService.createEmployee(dto);
        apiResponse.setData(er);
        apiResponse.setMessage("Tạo mới thành công !");
        apiResponse.setStatus("SUCCESS");

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
