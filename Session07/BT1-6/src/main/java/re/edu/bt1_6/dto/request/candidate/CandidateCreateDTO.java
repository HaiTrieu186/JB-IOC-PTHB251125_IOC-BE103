package re.edu.bt1_6.dto.request.candidate;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateCreateDTO {

    @NotBlank(message = "Lỗi: Tên không được để trống !")
    @Size(min = 5, max = 50, message = "Lỗi: Độ dài tên không hợp lệ (5-50 kí tự)")
    private String fullName;
    @NotBlank(message = "Lỗi: Email không được để trống")
    @Email
    private String email;
    @NotNull(message = "Lỗi: Tuổi không được để trống")
    @Min(value = 18, message = "Lỗi: Tuổi phải tối thiểu 18 tuổi")
    private int age;
    @NotNull(message = "Lỗi: Số năm kinh nghiệm không được để trống")
    @PositiveOrZero(message = "Lỗi: Số năm kinh nghiệm phải lớn hơn (hoặc bằng) 0")
    private int yearsOfExperience;
}
