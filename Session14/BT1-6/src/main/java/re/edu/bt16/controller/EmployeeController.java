package re.edu.bt16.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.bt16.dto.request.EmployeeCreateDTO;
import re.edu.bt16.dto.response.ApiResponse;
import re.edu.bt16.entity.Employee;
import re.edu.bt16.service.EmployeeService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private static final List<Employee> employees= new ArrayList<>();
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getEmployees(){
//        employees.add(new Employee(1L,"A",new BigDecimal(100000)));
//        employees.add(new Employee(2L,"A",new BigDecimal(100000)));
//        employees.add(new Employee(3L,"A",new BigDecimal(100000)));
//
//        return ResponseEntity.ok(employees);
        return null;
    };

    @PostMapping
    public ResponseEntity<?> addEmployee(
            @Valid @ModelAttribute EmployeeCreateDTO dto
            ) throws IOException {
        Employee e = employeeService.createEmployee(dto);

        ApiResponse<Employee> apiResponse = new ApiResponse<>();
        apiResponse.setStatus("SUCCESS");
        apiResponse.setData(e);
        apiResponse.setCode(HttpStatus.CREATED.value());
        apiResponse.setMessage("Tạo mới nhân viên thành công !");

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

}
