package com.dmit.service;

import com.dmit.dto.user.UserRequestDto;
import com.dmit.dto.user.UserResponseDto;
import com.dmit.entity.order.OrderStatus;
import org.springframework.security.access.annotation.Secured;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponseDto addUser(UserRequestDto userRequestDto);
    @Secured("ROLE_MANAGER")
    long countAllUsers();
    @Secured("ROLE_MANAGER")
    List<UserResponseDto> findAllUsersPageable(int page, int size);
    @Secured("ROLE_MANAGER")
    long countAllUsersByLocked(boolean locked);
    @Secured("ROLE_MANAGER")
    List<UserResponseDto> findAllUsersByLockedPageable(boolean locked, int page, int size);
    @Secured("ROLE_MANAGER")
    long countAllUsersByOrderStatus(OrderStatus orderStatus);
    @Secured("ROLE_MANAGER")
    List<UserResponseDto> findAllUsersByOrderStatusPageable(OrderStatus orderStatus, int page, int size);
    @Secured("ROLE_MANAGER")
    UserResponseDto findUserById(UUID userId);
    @Secured({"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
    UserResponseDto findCurrentUser();
}
