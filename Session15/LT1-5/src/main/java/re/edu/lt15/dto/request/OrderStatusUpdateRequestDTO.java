package re.edu.lt15.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusUpdateRequestDTO {

    @NotBlank(message = "Trạng thái không được để trống")
    private String status; // VD: PENDING, PROCESSING, COMPLETED, CANCELLED

}