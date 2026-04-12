package re.edu.bt16.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import re.edu.bt16.dto.request.EmployeeCreateDTO;
import re.edu.bt16.dto.response.EmployeeResponse;
import re.edu.bt16.entity.Department;
import re.edu.bt16.entity.Employee;
import re.edu.bt16.exception.DuplicateResourceException;
import re.edu.bt16.exception.ResourceNotFoundException;
import re.edu.bt16.mapper.EmployeeMapper;
import re.edu.bt16.repository.IDepartmentRepository;
import re.edu.bt16.repository.IEmployeeRepository;
import re.edu.bt16.service.IEmployeeService;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;
    private final IDepartmentRepository departmentRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeCreateDTO dto) {
        Employee e = new Employee();

        e.setFullName(dto.getFullName());

        // Kiểm tra trùng email
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Lỗi: email đã có người sử dụng");
        }

        // Kiểm tra tồn tại phòng ban
        Department d = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(
                () -> new ResourceNotFoundException("Lỗi: không tìm thấy phòng ban với id: " + dto.getDepartmentId())
        );

        e.setEmail(dto.getEmail());
        e.setPhone(dto.getPhone());
        e.setSalary(dto.getSalary());
        e.setDepartment(d);
        e= employeeRepository.save(e);

        return EmployeeMapper.mapEntityToResponse(e);
    }
}
