package re.edu.bt16.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import re.edu.bt16.dto.request.EmployeeCreateDTO;
import re.edu.bt16.dto.request.EmployeeUpdateDTO;
import re.edu.bt16.dto.response.EmployeeResponse;
import re.edu.bt16.entity.Department;
import re.edu.bt16.entity.Employee;
import re.edu.bt16.exception.DuplicateResourceException;
import re.edu.bt16.exception.ExceedFileSizeExtension;
import re.edu.bt16.exception.ResourceNotFoundException;
import re.edu.bt16.mapper.EmployeeMapper;
import re.edu.bt16.repository.IDepartmentRepository;
import re.edu.bt16.repository.IEmployeeRepository;
import re.edu.bt16.service.IEmployeeService;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {
    private final Cloudinary cloudinary;
    private final IEmployeeRepository employeeRepository;
    private final IDepartmentRepository departmentRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeCreateDTO dto) throws IOException {
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


        // Kiểm tra fila avatar
        String secureUrl="";
        if (dto.getAvatarFile() != null && !dto.getAvatarFile().isEmpty()) {

            if (dto.getAvatarFile().getSize() > 2 *1024 * 1024) {
                throw new ExceedFileSizeExtension("Lỗi: Kích thước file phải nhỏ hơn 2MB");
            }

            Map uploadResult = cloudinary.uploader()
                    .upload(dto.getAvatarFile().getBytes(), ObjectUtils.emptyMap());

            // Lấy ra đường dẫn truy cập
            secureUrl = (String) uploadResult.get("secure_url");

            if (secureUrl == null || secureUrl.isEmpty()) {
                throw new IOException("Lỗi upload: Không tìm thấy URL khi tải ảnh");
            }
        };

        e.setAvatarUrl(secureUrl);
        e.setFullName(dto.getFullName());
        e.setEmail(dto.getEmail());
        e.setPhone(dto.getPhone());
        e.setSalary(dto.getSalary());
        e.setDepartment(d);
        e= employeeRepository.save(e);

        return EmployeeMapper.mapEntityToResponse(e);
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeUpdateDTO dto, Long empId) throws IOException {
        Employee e = employeeRepository.findById(empId).orElseThrow(
                () -> new ResourceNotFoundException("Lỗi: Không tìm thấy nhân viên với id: "+empId)
        );

        // Kiểm tra fila avatar
        String secureUrl="";
        if (dto.getAvatarFile() != null && !dto.getAvatarFile().isEmpty()) {

            if (dto.getAvatarFile().getSize() > 2 *1024 * 1024) {
                throw new ExceedFileSizeExtension("Lỗi: Kích thước file phải nhỏ hơn 2MB");
            }

            Map uploadResult = cloudinary.uploader()
                    .upload(dto.getAvatarFile().getBytes(), ObjectUtils.emptyMap());

            // Lấy ra đường dẫn truy cập
            secureUrl = (String) uploadResult.get("secure_url");

            if (secureUrl == null || secureUrl.isEmpty()) {
                throw new IOException("Lỗi upload: Không tìm thấy URL khi tải ảnh");
            }
        } else {
            throw new IllegalArgumentException("Lỗi: Bắt buộc phải tải ảnh lên!");
        }

        e.setAvatarUrl(secureUrl);
        e=employeeRepository.save(e);
        return EmployeeMapper.mapEntityToResponse(e);
    }


}
