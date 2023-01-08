package com.dmit.service;

import com.dmit.dao.RoleDao;
import com.dmit.dao.UserDao;
import com.dmit.dto.user.UserDto;
import com.dmit.dto.user.UserResponseDto;
import com.dmit.entity.user.Role;
import com.dmit.entity.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
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

    @Transactional
    public void registerUser(UserDto userRequestDto) {
        final String DEFAULT_ROLE = "USER";

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userRequestDto);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<UserDto> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
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

        user.setId(null); // In case we get input with id somehow
        user.setLocked(false);

        user.getUserDetail().setUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> role = roleDao.findByRoleName(DEFAULT_ROLE);
        if (role.isEmpty()) {
            roleDao.save(new Role(null, DEFAULT_ROLE, new HashSet<>()));
            role = roleDao.findByRoleName(DEFAULT_ROLE);
        }

        user.addRole(role.orElseThrow()); // TODO: Custom exception

        userDao.save(user);
    }

    @Transactional
    public UserDto findUserByUsername(String username) {
        // TODO NullPointer?
        return modelMapper.map(userDao.findByUsername(username), UserDto.class);
    }

    @Transactional
    @Secured("ROLE_ADMIN")
    public long countUsers() {
        return userDao.count();
    }

    @Transactional
    @Secured("ROLE_ADMIN")
    public List<UserResponseDto> getAllUsersPageable(int page, int size) {
        return userDao.findAll(PageRequest.of(page, size)).stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Secured("ROLE_ADMIN")
    public UserResponseDto findUserById(UUID userId) {
        Optional<User> user = userDao.findById(userId);
        return modelMapper.map(user.orElseThrow(), UserResponseDto.class);
        // TODO: custom exception
    }
}
