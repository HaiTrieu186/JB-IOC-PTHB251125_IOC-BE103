package re.edu.bt16.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import re.edu.bt16.validation.ValidFileExtension;

@Getter
@Setter
public class EmployeeUpdateAvatarDTO {
    @NotNull(message = "Lỗi: không được để trống avatar")
    @ValidFileExtension(extensions = {"png","jpg","jpeg"})
    private MultipartFile avatarFile;
}
