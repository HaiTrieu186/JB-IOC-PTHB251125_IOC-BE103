package re.edu.bt16.service;

import re.edu.bt16.dto.request.EmployeeCreateDTO;
import re.edu.bt16.dto.response.EmployeeResponse;

public interface IEmployeeService {
    EmployeeResponse createEmployee(EmployeeCreateDTO dtp);
}
