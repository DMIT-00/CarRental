package com.dmit.service;

import com.dmit.dao.RoleDao;
import com.dmit.dao.UserDao;
import com.dmit.dto.user.UserDto;
import com.dmit.entity.user.Role;
import com.dmit.entity.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

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
        user.getUserDetail().setUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleDao.findByRoleName("USER")
                .orElse(roleDao.save(new Role(null, "USER", new HashSet<>())));

        user.addRole(role);

        userDao.save(user);
    }

    @Transactional
    public UserDto findUserByUsername(String username) {
        // TODO NullPointer?
        return modelMapper.map(userDao.findByUsername(username), UserDto.class);
    }
}
