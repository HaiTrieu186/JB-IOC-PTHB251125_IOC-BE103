package re.edu.lt15.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import re.edu.lt15.dto.response.OrderItemResponse;
import re.edu.lt15.dto.response.OrderResponse;
import re.edu.lt15.entity.Order;
import re.edu.lt15.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    // 1. Map OrderItem -> OrderItemResponse
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    // 2. Map Order -> OrderResponse
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.email", target = "userEmail")
    @Mapping(source = "orderItems", target = "items")
    OrderResponse toOrderResponse(Order order);
}