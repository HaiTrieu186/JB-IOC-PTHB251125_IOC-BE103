package re.edu.bt16.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import re.edu.bt16.validation.ValidFileExtension;

@Getter
@Setter
public class EmployeeCreateDTO {
    @NotBlank(message = "Lỗi: không được để trống họ và tên !")
    private String fullName;
    @Email(message = "Lỗi: định dạng email không hợp lệ !")
    private String email;
    @NotBlank(message = "Lỗi: không được để trống tên phòng ban !")
    private String department;
    @NotNull(message = "Lỗi: không được để trống file CV")
    @ValidFileExtension(extensions = {"png","jpg","jpeg"})
    private MultipartFile avatarFile;
}
