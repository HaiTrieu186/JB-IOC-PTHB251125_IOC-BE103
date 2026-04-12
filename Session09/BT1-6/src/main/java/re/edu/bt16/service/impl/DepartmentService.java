package re.edu.bt16.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.bt16.dto.request.DepartmentDTO;
import re.edu.bt16.dto.response.DepartmentResponse;
import re.edu.bt16.entity.Department;
import re.edu.bt16.mapper.DepartmentMapper;
import re.edu.bt16.repository.IDepartmentRepository;
import re.edu.bt16.service.IDepartmentService;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {
    private final IDepartmentRepository departmentRepository;

    @Override
    public DepartmentResponse createDepartment(DepartmentDTO dto) {
        Department department = new Department();

        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        department= departmentRepository.save(department);

        return DepartmentMapper.mapEntityToResponse(department);
    }
}
