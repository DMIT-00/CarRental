package com.dmit.dao;

import com.dmit.entity.order.OrderStatus;
import com.dmit.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, UUID> {
    User findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByUsernameAndIdNot(String username, UUID id);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);
    boolean existsByUserDetail_PhoneNumber(String phoneNumber);
    boolean existsByUserDetail_PhoneNumberAndIdNot(String phoneNumber, UUID id);
    boolean existsByUserDetail_CreditCard(String creditCard);
    boolean existsByUserDetail_CreditCardAndIdNot(String creditCard, UUID id);

    long countByLocked(boolean locked);
    Page<User> findAllByLocked(boolean locked, Pageable pageable);
    long countByOrdersOrderStatus(OrderStatus orderStatus);
    Page<User> findDistinctByOrders_OrderStatus(OrderStatus orderStatus, Pageable pageable);
}
