package com.dmit.dao;

import com.dmit.entity.order.Order;
import com.dmit.entity.order.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderDao extends JpaRepository<Order, UUID> {
    long countByOrderStatus(OrderStatus orderStatus);
    Page<Order> findAllByOrderStatus(OrderStatus orderStatus, Pageable pageable);
}
