package re.edu.bt16.dto.request.reader;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import re.edu.bt16.validation.FileExtension;

@Getter
@Setter
public class ReaderCreateDTO {
    @Email(message = "Lỗi: Định dạng email không hợp lệ")
    @NotBlank(message = "Lỗi: Không được để trống email")
    private String email;
    @NotBlank(message = "Lỗi: Không được để trống họ tên")
    private String fullName;
    @Pattern(regexp = "^0[35789][0-9]{8}$", message = "Lỗi: định dạng sdt không hợp lệ")
    private String phoneNumber;
    @NotBlank(message = "Lỗi: Không được để trống địa chỉ")
    private String address;
    @NotNull
    @FileExtension(extensions = {"png","jpg","jpeg"}, message = "Lỗi: Định dạng file không hợp lệ")
    private MultipartFile avatarFile;
}
