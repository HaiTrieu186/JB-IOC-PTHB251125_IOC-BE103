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
public class CandidateApplyDTO {
    @NotBlank(message = "Lỗi: Không được để trống tên ứng viên")
    private String name;

    @NotBlank(message = "Lỗi: Không được để trống email ứng viên ")
    @Email(message = "Lỗi: Định dạng Email không hợp lệ")
    private String email;
    @NotNull(message = "Lỗi: không được để trống file CV")
    @ValidFileExtension(extensions = {"pdf"}, message = "Lỗi: Định dạng file không hợp lệ (Chỉ nhận PDF)")
    private MultipartFile cvFile;
}
