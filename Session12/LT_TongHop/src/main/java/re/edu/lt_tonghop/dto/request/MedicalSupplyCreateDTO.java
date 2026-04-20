package re.edu.lt_tonghop.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalSupplyCreateDTO {

    @NotBlank(message = "Lỗi: Tên vật tư không được để trống")
    private String name;

    private String provider;

    private String unit;

    private String specification;

    @Min(value = 0, message = "Lỗi: Số lượng không được nhỏ hơn 0")
    private Integer quantity;
}
