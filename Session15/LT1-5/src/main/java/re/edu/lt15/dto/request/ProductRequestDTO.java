package re.edu.lt15.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequestDTO {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    private String description; // Mô tả cũng có thế trống

    @NotNull(message = "Giá sản phẩm không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá sản phẩm phải lớn hơn 0")
    private BigDecimal price;

    @NotBlank(message = "Size không được để trống (VD: S, M, L)")
    private String size;

    private String toppings; // Topping có thể rỗng
}
