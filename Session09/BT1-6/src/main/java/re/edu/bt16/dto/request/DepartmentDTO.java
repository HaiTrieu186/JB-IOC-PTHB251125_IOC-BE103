package re.edu.bt16.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepartmentDTO {
    @NotBlank(message = "Lỗi: Không được để trống tên phòng ban")
    @Size(min=5, max=50, message = "Lỗi: Độ dài tên phòng ban phải nằm trong khoảng 5-50 ký tự")
    private String name;
    @Max(50)
    private String description;
}
