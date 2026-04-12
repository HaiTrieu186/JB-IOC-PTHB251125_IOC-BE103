package re.edu.bt16.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EmployeeCreateDTO {
    @NotBlank(message = "Lỗi: tên không được để trống")
    private String fullName;
    @Email(message = "Lỗi: Email không đúng định dạng")
    private String email;
    @Pattern(regexp = "^0[35789][0-9]{8}$", message = "Lỗi: SDT không đúng định dạng")
    private String phone;
    @Min(value = 5000000, message = "Lỗi: lương phải tối thiểu 5.000.000 VNĐ")
    private BigDecimal salary;
    @NotNull(message = "Lỗi: Không được để trống mã phòng ban")
    private Long departmentId;
}
