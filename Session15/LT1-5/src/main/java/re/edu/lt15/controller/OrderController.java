package re.edu.lt15.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import re.edu.lt15.dto.request.OrderRequestDTO;
import re.edu.lt15.dto.request.OrderStatusUpdateRequestDTO;
import re.edu.lt15.dto.response.ApiResponse;
import re.edu.lt15.dto.response.OrderResponse;
import re.edu.lt15.dto.response.PageResponse;
import re.edu.lt15.service.IOrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final IOrderService orderService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public ResponseEntity<?> createOrder(
            @Valid @RequestBody OrderRequestDTO request
    ) {
        OrderResponse orderResponse = orderService.createOrder(request);
        ApiResponse<OrderResponse> response = new ApiResponse<>(
                "SUCCESS",
                HttpStatus.CREATED.value(),
                "Đặt hàng thành công",
                orderResponse
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/my")
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER')")
    public ResponseEntity<?> getMyOrders(){
        List<OrderResponse> responseList = orderService.getMyOrders();
        ApiResponse<List<OrderResponse>> response = new ApiResponse<>(
                "SUCCESS",
                HttpStatus.OK.value(),
                "Lấy danh sách đơn hàng cá nhân thành công",
                responseList
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_STAFF', 'ROLE_ADMIN')")
    public ResponseEntity<?> getAllOrders(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ){
        PageResponse<OrderResponse> responseList = orderService.getAllOrders(pageable);
        ApiResponse<PageResponse<OrderResponse>> response = new ApiResponse<>(
                "SUCCESS",
                HttpStatus.OK.value(),
                "Lấy danh sách đơn hàng thành công",
                responseList
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyAuthority('ROLE_STAFF')")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable("id") Long orderId,
            @Valid @RequestBody OrderStatusUpdateRequestDTO request
    ){
        OrderResponse o = orderService.updateOrderStatus(orderId, request);

        ApiResponse<OrderResponse> response = new ApiResponse<>(
                "SUCCESS",
                HttpStatus.OK.value(),
                "Cập nhật trạng thái đơn đơn hàng thành công",
                o
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}


