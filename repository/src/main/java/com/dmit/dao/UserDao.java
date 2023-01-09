package com.dmit.dao;

import com.dmit.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    User findByUsername(String username);
    User findByUserDetail_CreditCard(String creditCard);
    User findByUserDetail_PhoneNumber(String phoneNumber);
    long countByLockedTrue();
    long countByLockedFalse();
    long countByActiveOrderNotNull();
    long countByActiveOrderIsNull();

    Page<User> findAllByLockedTrue(Pageable pageable);
    Page<User> findAllByLockedFalse(Pageable pageable);
    Page<User> findAllByActiveOrderNotNull(Pageable pageable);
    Page<User> findAllByActiveOrderIsNull(Pageable pageable);
}
