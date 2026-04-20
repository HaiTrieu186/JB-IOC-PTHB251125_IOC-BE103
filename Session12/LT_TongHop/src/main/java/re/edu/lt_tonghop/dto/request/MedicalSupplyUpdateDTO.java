package re.edu.lt_tonghop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalSupplyUpdateDTO {
    private Long id;
    private Integer quantity;

    @NotBlank(message = "Lỗi: Tên vật tư không được để trống")
    private String name;

    private String provider;

    private String specification;
}