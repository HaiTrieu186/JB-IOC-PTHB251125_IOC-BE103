package re.edu.lt15.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import re.edu.lt15.config.security.UserPrincipal;
import re.edu.lt15.dto.request.OrderItemRequestDTO;
import re.edu.lt15.dto.request.OrderRequestDTO;
import re.edu.lt15.dto.request.OrderStatusUpdateRequestDTO;
import re.edu.lt15.dto.response.OrderItemResponse;
import re.edu.lt15.dto.response.OrderResponse;
import re.edu.lt15.dto.response.PageResponse;
import re.edu.lt15.entity.Order;
import re.edu.lt15.entity.OrderItem;
import re.edu.lt15.entity.Product;
import re.edu.lt15.entity.User;
import re.edu.lt15.mapper.OrderMapper;
import re.edu.lt15.repository.OrderItemRepository;
import re.edu.lt15.repository.OrderRepository;
import re.edu.lt15.repository.ProductRepository;
import re.edu.lt15.service.IOrderService;
import re.edu.lt15.utils.PageUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    private User getCurrentUser() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getUser();
    }


    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequestDTO request) {
        // 1. Lấy user hiện tại (từ token)
        User currentUser = getCurrentUser();
        List<OrderItem> orderItems = new ArrayList<>();

        // 2. Tạo đơn hàng mới
        Order order = new Order();
        order.setUser(currentUser);
        order.setCreatedDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setTotalMoney(BigDecimal.ZERO);
        order = orderRepository.save(order);

        // 3. Lấy danh sách sản phẩm
        for (OrderItemRequestDTO item : request.getItems()) {
            // lấy sản phẩm
            Product product = productRepository.findById(item.getProductId()).orElseThrow(
                    () -> new RuntimeException("Không tìm thấy sản phẩm với id: "+item.getProductId())
            );

            // Tạo order item mới
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPriceBuy(product.getPrice());
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItems.add(orderItem);
        }

        // 4. Lưu tất cả OrderItem
        orderItemRepository.saveAll(orderItems);

        // 5. Tính tổng tiền từ list
        BigDecimal total = orderItems.stream()
                .map(i -> i.getPriceBuy().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 6. Cập nhật lại Order rồi save lần 2
        order.setTotalMoney(total);
        order.setOrderItems(orderItems);
        order=orderRepository.save(order);


        // 7. Map và return
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getMyOrders() {
        User currentUser = getCurrentUser();

        List<OrderResponse> orders = orderRepository.findOrderByUser(currentUser).stream().map(
                orderMapper::toOrderResponse
        ).toList();

        return orders;
    }

    @Override
    public PageResponse<OrderResponse> getAllOrders(Pageable pageable) {
        Page<OrderResponse> orders = orderRepository.findAll(pageable).map(orderMapper::toOrderResponse);

        return PageUtils.toPageResponse(orders);
    }

    @Override
    @Transactional
    public OrderResponse updateOrderStatus(Long orderId, OrderStatusUpdateRequestDTO request) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Không tìm thấy đơn hàng với id: "+ orderId)
        );

        order.setStatus(request.getStatus());
        order = orderRepository.save(order);
        return orderMapper.toOrderResponse(order);
    }

}
