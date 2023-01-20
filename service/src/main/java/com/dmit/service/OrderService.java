package com.dmit.service;

import com.dmit.dto.order.OrderDto;
import com.dmit.dto.order.OrderRequestDto;
import com.dmit.entity.order.OrderStatus;
import org.springframework.security.access.annotation.Secured;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    @Secured("ROLE_USER")
    OrderDto addOrder(OrderRequestDto orderRequestDto);
    @Secured("ROLE_MANAGER")
    OrderDto updateOrder(OrderDto updatedOrder);
    @Secured("ROLE_MANAGER")
    void deleteOrder(UUID id);

    @Secured("ROLE_USER")
    boolean isUserBusyForOrder(UUID userId, LocalDateTime startDate, LocalDateTime endDate);
    @Secured("ROLE_USER")
    boolean isCarBusyForOrder(UUID carId, LocalDateTime startDate, LocalDateTime endDate);
    @Secured("ROLE_USER")
    boolean isUserBusyForOrderExceptOrder(UUID orderId, UUID userId, LocalDateTime startDate, LocalDateTime endDate);
    @Secured("ROLE_USER")
    boolean isCarBusyForOrderExceptOrder(UUID orderId, UUID carId, LocalDateTime startDate, LocalDateTime endDate);

    @Secured("ROLE_MANAGER")
    OrderDto findOrderById(UUID orderId);
    @Secured("ROLE_MANAGER")
    long countAllOrdersByStatus(OrderStatus orderStatus);
    @Secured("ROLE_MANAGER")
    List<OrderDto> findAllOrdersByStatusPageable(OrderStatus orderStatus, int page, int size);
    @Secured("ROLE_MANAGER")
    long countAllOrders();
    @Secured("ROLE_MANAGER")
    List<OrderDto> findAllOrdersPageable(int page, int size);
}
