package re.edu.bt16.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.edu.bt16.entity.Employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private static final List<Employee> employees= new ArrayList<>();

    @GetMapping
    public ResponseEntity<?> getEmployees(){
        employees.add(new Employee(1L,"A",new BigDecimal(100000)));
        employees.add(new Employee(2L,"A",new BigDecimal(100000)));
        employees.add(new Employee(3L,"A",new BigDecimal(100000)));

        return ResponseEntity.ok(employees);
    }

}
