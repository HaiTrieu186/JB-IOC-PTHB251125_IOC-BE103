package re.edu.bt16.service;

import re.edu.bt16.dto.request.DepartmentDTO;
import re.edu.bt16.dto.response.DepartmentResponse;

public interface IDepartmentService {
    DepartmentResponse createDepartment(DepartmentDTO dto);
}
