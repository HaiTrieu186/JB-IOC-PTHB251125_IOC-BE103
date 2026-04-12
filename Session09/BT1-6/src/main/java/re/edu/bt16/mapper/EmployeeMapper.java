package re.edu.bt16.mapper;

import re.edu.bt16.dto.response.EmployeeResponse;
import re.edu.bt16.entity.Employee;

public class EmployeeMapper {
    public static EmployeeResponse mapEntityToResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .salary(employee.getSalary())
                .avatarUrl(employee.getAvatarUrl())
                .departmentId(employee.getDepartment().getId())
                .build();
    }
}
