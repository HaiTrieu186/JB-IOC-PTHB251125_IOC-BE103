package re.edu.bt16.mapper;

import re.edu.bt16.dto.response.DepartmentResponse;
import re.edu.bt16.entity.Department;

public class DepartmentMapper {
    public static DepartmentResponse mapEntityToResponse (Department department){
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .build();
    }
}
