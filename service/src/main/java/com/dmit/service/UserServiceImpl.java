package com.dmit.service;

import com.dmit.dao.RoleDao;
import com.dmit.dao.UserDao;
import com.dmit.dto.user.UserRequestDto;
import com.dmit.dto.user.UserResponseDto;
import com.dmit.entity.order.OrderStatus;
import com.dmit.entity.user.Role;
import com.dmit.entity.user.User;
import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.NotFoundException;
import org.modelmapper.ModelMapper;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private Validator validator;
    @Autowired
    private ModelMapper modelMapper;
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

        if (userDao.findByUsername(userRequestDto.getUsername()) != null)
            throw new IllegalArgumentException("Username is already used to register");

        if (userDao.findByEmail(userRequestDto.getEmail()) != null)
            throw new IllegalArgumentException("Email is already used to register");

        if (userDao.findByUserDetail_PhoneNumber(userRequestDto.getUserDetail().getPhoneNumber()) != null)
            throw new IllegalArgumentException("Phone number is already used to register");

        if (userDao.findByUserDetail_CreditCard(userRequestDto.getUserDetail().getCreditCard()) != null)
            throw new IllegalArgumentException("Credit card is already used to register");

        User user = modelMapper.map(userRequestDto, User.class);

        // Check for duplicate Id
        if (user.getId() != null && userDao.findById(user.getId()).isPresent()) {
            throw new AlreadyExistsException("User already exists! Id: " + user.getId());
        }

        user.setLocked(false);

        user.getUserDetail().setUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // TODO: shouldn't do that
        Optional<Role> role = roleDao.findByRoleName(DEFAULT_ROLE);
        if (role.isEmpty()) {
            roleDao.save(new Role(null, DEFAULT_ROLE, new HashSet<>()));
            role = roleDao.findByRoleName(DEFAULT_ROLE);
        }

        user.addRole(role.orElseThrow(() -> new NotFoundException("Role not found! Name: " + DEFAULT_ROLE)));

        userDao.save(user);
        
        return modelMapper.map(user, UserResponseDto.class);
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
                .map(user -> modelMapper.map(user, UserResponseDto.class))
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
                .map(user -> modelMapper.map(user, UserResponseDto.class))
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
        Page<User> users = userDao.findAllByOrders_OrderStatus(orderStatus, PageRequest.of(page, size));
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public UserResponseDto findUserById(UUID userId) {
        User user = userDao.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found! Id: " + userId));

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    @Transactional
    @Secured({"ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN"})
    public UserResponseDto findCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.findByUsername(auth.getName());
        if (user == null)
            throw new UsernameNotFoundException("User not found! Username: " + auth.getName());

        return modelMapper.map(user, UserResponseDto.class);
    }
}
