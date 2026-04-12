package re.edu.bt16.service;

import re.edu.bt16.dto.request.EmployeeCreateDTO;
import re.edu.bt16.dto.request.EmployeeUpdateAvatarDTO;
import re.edu.bt16.dto.response.EmployeeResponse;

import java.io.IOException;

public interface IEmployeeService {
    EmployeeResponse createEmployee(EmployeeCreateDTO dto) throws IOException;
    EmployeeResponse updateEmployee(EmployeeUpdateAvatarDTO dto, Long empId) throws IOException;
}
