package re.edu.bt16.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.bt16.dto.ApiResponse;
import re.edu.bt16.dto.request.EmployeeCreateDTO;
import re.edu.bt16.dto.request.EmployeeUpdateDTO;
import re.edu.bt16.dto.response.EmployeeResponse;
import re.edu.bt16.entity.Employee;
import re.edu.bt16.service.IEmployeeService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final IEmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @ModelAttribute EmployeeCreateDTO dto) throws IOException {
        ApiResponse<EmployeeResponse> apiResponse = new ApiResponse<>();

        EmployeeResponse er = employeeService.createEmployee(dto);
        apiResponse.setData(er);
        apiResponse.setMessage("Tạo mới thành công !");
        apiResponse.setStatus("SUCCESS");

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/avatar")
    public  ResponseEntity<?> updateAvatarEmployee(
            @Valid @ModelAttribute EmployeeUpdateDTO dto,
            @PathVariable Long id) throws IOException {
        ApiResponse<EmployeeResponse> apiResponse = new ApiResponse<>();
        EmployeeResponse er = employeeService.updateEmployee(dto, id);
        apiResponse.setData(er);
        apiResponse.setStatus("SUCCESS");
        apiResponse.setMessage("Cập nhật avatar thành công");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
