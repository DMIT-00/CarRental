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
import lombok.RequiredArgsConstructor;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final ValidationService<UserRequestDto> validationService;
    private final UserResponseDtoMapper userResponseDtoMapper;
    private final UserRequestDtoMapper userRequestDtoMapper;
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        final String DEFAULT_ROLE = "USER";

        validationService.validate(userRequestDto);

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
    @Transactional
    @Secured("ROLE_ADMIN")
    public UserResponseDto updateUser(UserRequestDto updatedUser) {
        validationService.validate(updatedUser);

        if (userDao.existsByUsernameAndIdNot(updatedUser.getUsername(), updatedUser.getId()))
            throw new AlreadyExistsException("Username is already used to register");

        if (userDao.existsByEmailAndIdNot(updatedUser.getEmail(), updatedUser.getId()))
            throw new AlreadyExistsException("Email is already used to register");

        if (userDao.existsByUserDetail_PhoneNumberAndIdNot(updatedUser.getUserDetail().getPhoneNumber(), updatedUser.getId()))
            throw new AlreadyExistsException("Phone number is already used to register");

        if (userDao.existsByUserDetail_CreditCardAndIdNot(updatedUser.getUserDetail().getCreditCard(), updatedUser.getId()))
            throw new AlreadyExistsException("Credit card is already used to register");

        User user = userDao.findById(updatedUser.getId())
                .orElseThrow(() -> new NotFoundException("User does not exist! Id: " + updatedUser.getId()));

        user.setUsername(updatedUser.getUsername());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        user.setEmail(updatedUser.getEmail());

        user.getUserDetail().setBirthDate(updatedUser.getUserDetail().getBirthDate());
        user.getUserDetail().setCreditCard(updatedUser.getUserDetail().getCreditCard());
        user.getUserDetail().setFirstName(updatedUser.getUserDetail().getFirstName());
        user.getUserDetail().setLastName(updatedUser.getUserDetail().getLastName());
        user.getUserDetail().setPhoneNumber(updatedUser.getUserDetail().getPhoneNumber());

        return userResponseDtoMapper.toDto(userDao.save(user));
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
                .map(userResponseDtoMapper::toDto)
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
                .map(userResponseDtoMapper::toDto)
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
                .map(userResponseDtoMapper::toDto)
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
