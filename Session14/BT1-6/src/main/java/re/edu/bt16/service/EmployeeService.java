package re.edu.bt16.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.bt16.dto.request.EmployeeCreateDTO;
import re.edu.bt16.entity.Employee;
import re.edu.bt16.exception.DuplicateResourceException;
import re.edu.bt16.repository.IEmployeeRepository;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final Cloudinary cloudinary;
    private final IEmployeeRepository employeeRepository;

    public Employee createEmployee(EmployeeCreateDTO dto) throws IOException {

        if (employeeRepository.existsByEmail(dto.getEmail()))
            throw new DuplicateResourceException("Lỗi: email đã có người sử dụng");

        // Kiểm tra fila avatar
        String secureUrl="";
        if (dto.getAvatarFile() != null && !dto.getAvatarFile().isEmpty()) {

//            if (dto.getAvatarFile().getSize() > 2 *1024 * 1024) {
//                throw new ExceedFileSizeExtension("Lỗi: Kích thước file phải nhỏ hơn 2MB");
//            }

            Map uploadResult = cloudinary.uploader()
                    .upload(dto.getAvatarFile().getBytes(), ObjectUtils.emptyMap());

            // Lấy ra đường dẫn truy cập
            secureUrl = (String) uploadResult.get("secure_url");

            if (secureUrl == null || secureUrl.isEmpty()) {
                throw new IOException("Lỗi upload: Không tìm thấy URL khi tải ảnh");
            }
        };


        Employee e = new Employee();
        e.setAvatarUrl(secureUrl);
        e.setFullName(dto.getFullName());
        e.setEmail(dto.getEmail());
        e.setDepartment(dto.getDepartment());
        e= employeeRepository.save(e);

        return e;
    }
}
