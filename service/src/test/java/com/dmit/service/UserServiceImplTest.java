package com.dmit.service;

import com.dmit.dao.RoleDao;
import com.dmit.dao.UserDao;
import com.dmit.dto.user.UserDetailDto;
import com.dmit.dto.user.UserRequestDto;
import com.dmit.entity.user.Role;
import com.dmit.entity.user.User;
import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Validator;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Spy
    ModelMapper modelMapper = new ModelMapper();
    @Mock
    Validator validator;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    RoleDao roleDao;
    @Mock
    UserDao userDao;
    @InjectMocks
    UserServiceImpl targetObject;

    public UserServiceImplTest() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    public void addNewUserShouldThrowOnDuplicateId() {
        // Given
        UserRequestDto userDto = new UserRequestDto();
        userDto.setId(UUID.randomUUID());
        userDto.setUserDetail(new UserDetailDto());
        userDto.getUserDetail().setCreditCard("0000000000000000");
        userDto.getUserDetail().setPhoneNumber("+3200000");

        // When
        when(userDao.existsById(userDto.getId()))
                .thenReturn(true);

        // Then
        assertThrows(AlreadyExistsException.class, () -> targetObject.addUser(userDto));
    }

    @Test
    public void addNewUserShouldThrowOnDuplicateCreditCard() {
        // Given
        UserRequestDto userDto = new UserRequestDto();
        userDto.setId(UUID.randomUUID());
        userDto.setUserDetail(new UserDetailDto());
        userDto.getUserDetail().setCreditCard("0000000000000000");
        userDto.getUserDetail().setPhoneNumber("+3200000");

        // When
        when(userDao.existsByUserDetail_CreditCard(userDto.getUserDetail().getCreditCard()))
                .thenReturn(true);

        // Then
        assertThrows(AlreadyExistsException.class, () -> targetObject.addUser(userDto));
    }

    @Test
    public void addNewUserShouldThrowOnDuplicateEmail() {
        // Given
        UserRequestDto userDto = new UserRequestDto();
        userDto.setId(UUID.randomUUID());
        userDto.setEmail("myemail@gmail.com");
        userDto.setUserDetail(new UserDetailDto());
        userDto.getUserDetail().setCreditCard("0000000000000000");
        userDto.getUserDetail().setPhoneNumber("+3200000");

        // When
        when(userDao.existsByEmail(userDto.getEmail()))
                .thenReturn(true);

        // Then
        assertThrows(AlreadyExistsException.class, () -> targetObject.addUser(userDto));
    }

    @Test
    public void addNewUserShouldThrowOnDuplicateUsername() {
        // Given
        UserRequestDto userDto = new UserRequestDto();
        userDto.setId(UUID.randomUUID());
        userDto.setUsername("myuser");
        userDto.setUserDetail(new UserDetailDto());
        userDto.getUserDetail().setCreditCard("0000000000000000");
        userDto.getUserDetail().setPhoneNumber("+3200000");

        // When
        when(userDao.existsByUsername(userDto.getUsername()))
                .thenReturn(true);

        // Then
        assertThrows(AlreadyExistsException.class, () -> targetObject.addUser(userDto));
    }

    @Test
    public void addNewUserShouldThrowOnDuplicatePhoneNumber() {
        // Given
        UserRequestDto userDto = new UserRequestDto();
        userDto.setId(UUID.randomUUID());
        userDto.setUsername("myuser");
        userDto.setUserDetail(new UserDetailDto());
        userDto.getUserDetail().setCreditCard("0000000000000000");
        userDto.getUserDetail().setPhoneNumber("+3200000");

        // When
        when(userDao.existsByUserDetail_PhoneNumber(userDto.getUserDetail().getPhoneNumber()))
                .thenReturn(true);

        // Then
        assertThrows(AlreadyExistsException.class, () -> targetObject.addUser(userDto));
    }

    @Test
    public void addNewUserShouldCallUserDao() {
        // Given
        UserRequestDto userDto = new UserRequestDto();
        userDto.setId(UUID.randomUUID());
        userDto.setUsername("myuser");
        userDto.setEmail("myemail@gmail.com");
        userDto.setPassword("password");
        userDto.setUserDetail(new UserDetailDto());
        userDto.getUserDetail().setCreditCard("0000000000000000");
        userDto.getUserDetail().setPhoneNumber("+3200000");

        // When
        when(roleDao.findByRoleName(any())).thenReturn(Optional.of(new Role(1L, "USER", new HashSet<>())));
        targetObject.addUser(userDto);

        // Then
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(userDao).save(argument.capture());
        assertEquals(argument.getValue().getId(), userDto.getId());
        assertEquals(argument.getValue().getUsername(), userDto.getUsername());
        assertEquals(argument.getValue().getEmail(), userDto.getEmail());
        assertEquals(argument.getValue().getUserDetail().getCreditCard(), userDto.getUserDetail().getCreditCard());
        assertEquals(argument.getValue().getUserDetail().getPhoneNumber(), userDto.getUserDetail().getPhoneNumber());
    }

    @Test
    public void deleteUserShouldThrowWhenUserDoesNotExist() {
        // Given
        UUID id = UUID.randomUUID();

        // When
        when(userDao.existsById(id))
                .thenReturn(false);

        // Then
        assertThrows(NotFoundException.class, () -> targetObject.deleteUser(id));
    }

    @Test
    public void deleteUserShouldCallDao() {
        // Given
        UUID id = UUID.randomUUID();

        // When
        when(userDao.existsById(id))
                .thenReturn(true);
        targetObject.deleteUser(id);

        // Then
        ArgumentCaptor<UUID> argument = ArgumentCaptor.forClass(UUID.class);
        verify(userDao).deleteById(argument.capture());
        assertEquals(argument.getValue(), id);
    }

    // TODO: more tests
}