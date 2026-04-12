package re.edu.bt16.service;

import re.edu.bt16.dto.request.EmployeeCreateDTO;
import re.edu.bt16.dto.request.EmployeeUpdateDTO;
import re.edu.bt16.dto.response.EmployeeResponse;

import java.io.IOException;

public interface IEmployeeService {
    EmployeeResponse createEmployee(EmployeeCreateDTO dto) throws IOException;
    EmployeeResponse updateEmployee(EmployeeUpdateDTO dto, Long empId) throws IOException;
}
