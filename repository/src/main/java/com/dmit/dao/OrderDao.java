package com.dmit.dao;

import com.dmit.entity.order.Order;
import com.dmit.entity.order.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;

public interface OrderDao extends JpaRepository<Order, UUID> {
    long countByOrderStatus(OrderStatus orderStatus);

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE car.id = :carId " +
            "AND orderStatus <> com.dmit.entity.order.OrderStatus.CLOSED " +
            "AND (:startDate BETWEEN startDate AND endDate OR :endDate BETWEEN startDate AND endDate)")
    long countActiveOrdersByCarInDateInterval(
            @Param("carId") UUID carId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE user.id = :userId " +
            "AND orderStatus <> com.dmit.entity.order.OrderStatus.CLOSED " +
            "AND (:startDate BETWEEN startDate AND endDate OR :endDate BETWEEN startDate AND endDate)")
    long countActiveOrdersByUserInDateInterval(
            @Param("userId") UUID userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE car.id = :carId " +
            "AND id <> :orderId " +
            "AND orderStatus <> com.dmit.entity.order.OrderStatus.CLOSED " +
            "AND (:startDate BETWEEN startDate AND endDate OR :endDate BETWEEN startDate AND endDate)")
    long countActiveOrdersByCarInDateIntervalExceptOrderWithId(
            @Param("orderId") UUID orderId,
            @Param("carId") UUID carId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE user.id = :userId " +
            "AND id <> :orderId " +
            "AND orderStatus <> com.dmit.entity.order.OrderStatus.CLOSED " +
            "AND (:startDate BETWEEN startDate AND endDate OR :endDate BETWEEN startDate AND endDate)")
    long countActiveOrdersByUserInDateIntervalExceptOrderWithId(
            @Param("orderId") UUID orderId,
            @Param("userId") UUID userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    Page<Order> findAllByOrderStatus(OrderStatus orderStatus, Pageable pageable);

}
