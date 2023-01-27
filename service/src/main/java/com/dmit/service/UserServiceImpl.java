package com.dmit.service;

import com.dmit.dao.RoleDao;
import com.dmit.dao.UserDao;
import com.dmit.dto.mapper.UserRequestDtoMapper;
import com.dmit.dto.mapper.UserResponseDtoMapper;
import com.dmit.dto.user.UserRequestDto;
import com.dmit.dto.user.UserResponseDto;
import com.dmit.entity.order.OrderStatus;
import com.dmit.entity.user.Role;
import com.dmit.entity.user.User;
import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private Validator validator;
    @Autowired
    private UserResponseDtoMapper userResponseDtoMapper;
    @Autowired
    private UserRequestDtoMapper userRequestDtoMapper;
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        final String DEFAULT_ROLE = "USER";

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);

        if (!violations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<UserRequestDto> constraintViolation : violations) {
                errors.append(constraintViolation.getPropertyPath())
                        .append(" ")
                        .append(constraintViolation.getMessage())
                        .append("; ");
            }
            throw new ConstraintViolationException("Validation errors: " + errors, violations);
        }

        if (userDao.existsByUsername(userRequestDto.getUsername()))
            throw new AlreadyExistsException("Username is already used to register");

        if (userDao.existsByEmail(userRequestDto.getEmail()))
            throw new AlreadyExistsException("Email is already used to register");

        if (userDao.existsByUserDetail_PhoneNumber(userRequestDto.getUserDetail().getPhoneNumber()))
            throw new AlreadyExistsException("Phone number is already used to register");

        if (userDao.existsByUserDetail_CreditCard(userRequestDto.getUserDetail().getCreditCard()))
            throw new AlreadyExistsException("Credit card is already used to register");

        User user = userRequestDtoMapper.fromDto(userRequestDto);

        // Check for duplicate Id
        if (user.getId() != null && userDao.existsById(user.getId())) {
            throw new AlreadyExistsException("User already exists! Id: " + user.getId());
        }

        user.setLocked(false);

        user.getUserDetail().setUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleDao.findByRoleName(DEFAULT_ROLE)
                .orElseThrow(() -> new NotFoundException("Role not found! Name: " + DEFAULT_ROLE));

        user.addRole(role);
        
        return userResponseDtoMapper.toDto(userDao.save(user));
    }

    @Override
    @Secured("ROLE_ADMIN")
    public UserResponseDto updateUser(UserRequestDto updatedUser) {
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(updatedUser);

        if (!violations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<UserRequestDto> constraintViolation : violations) {
                errors.append(constraintViolation.getPropertyPath())
                        .append(" ")
                        .append(constraintViolation.getMessage())
                        .append("; ");
            }
            throw new ConstraintViolationException("Validation errors: " + errors, violations);
        }

        // TODO: implement
        return null;
    }

    @Override
    @Secured("ROLE_ADMIN")
    public void deleteUser(UUID id) {
        if (!userDao.existsById(id))
            throw new NotFoundException("User not found! Id: " + id);

        // TODO: check for order

        userDao.deleteById(id);
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public long countAllUsers() {
        return userDao.count();
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public List<UserResponseDto> findAllUsersPageable(int page, int size) {
        return userDao.findAll(PageRequest.of(page, size)).stream()
                .map(user -> userResponseDtoMapper.toDto(user))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public long countAllUsersByLocked(boolean locked) {
        return userDao.countByLocked(locked);
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public List<UserResponseDto> findAllUsersByLockedPageable(boolean locked, int page, int size) {
        return userDao.findAllByLocked(locked, PageRequest.of(page, size)).stream()
                .map(user -> userResponseDtoMapper.toDto(user))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public long countAllUsersByOrderStatus(OrderStatus orderStatus) {
        return userDao.countByOrdersOrderStatus(orderStatus);
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public List<UserResponseDto> findAllUsersByOrderStatusPageable(OrderStatus orderStatus, int page, int size) {
        Page<User> users = userDao.findDistinctByOrders_OrderStatus(orderStatus, PageRequest.of(page, size));
        return users.stream()
                .map(user -> userResponseDtoMapper.toDto(user))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public UserResponseDto findUserById(UUID userId) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found! Id: " + userId));

        return userResponseDtoMapper.toDto(user);
    }

    @Override
    @Transactional
    @Secured({"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
    public UserResponseDto findCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.findByUsername(auth.getName());
        if (user == null)
            throw new UsernameNotFoundException("User not found! Username: " + auth.getName());

        return userResponseDtoMapper.toDto(user);
    }
}
