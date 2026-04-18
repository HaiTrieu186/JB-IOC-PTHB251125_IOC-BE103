package re.edu.bt36.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientCreateDTO {
    @NotBlank(message = "Tên bệnh nhân không được để trống")
    private String fullName;

    private String phoneNumber;

    @Min(value = 0, message = "Tuổi không hợp lệ")
    private Integer age;
}
