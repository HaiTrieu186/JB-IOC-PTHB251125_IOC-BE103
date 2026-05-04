package re.edu.lt15.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderResponse {
    private Long id;
    private LocalDateTime createdDate;
    private String status;
    private BigDecimal totalMoney;

    // Thông tin người mua
    private Long userId;
    private String userEmail;

    // Danh sách các món trong đơn hàng
    private List<OrderItemResponse> items;
}