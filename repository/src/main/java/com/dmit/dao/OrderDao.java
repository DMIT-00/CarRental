package com.dmit.dao;

import com.dmit.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderDao extends JpaRepository<Order, UUID> {
}
