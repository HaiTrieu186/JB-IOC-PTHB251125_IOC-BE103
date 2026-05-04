package re.edu.lt15.service;

import org.springframework.data.domain.Pageable;
import re.edu.lt15.dto.request.OrderRequestDTO;
import re.edu.lt15.dto.request.OrderStatusUpdateRequestDTO;
import re.edu.lt15.dto.response.OrderResponse;
import re.edu.lt15.dto.response.PageResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(OrderRequestDTO request);
    List<OrderResponse> getMyOrders();
    PageResponse<OrderResponse> getAllOrders(Pageable pageable);
    OrderResponse updateOrderStatus(Long orderId,OrderStatusUpdateRequestDTO request);

}
