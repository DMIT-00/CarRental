package com.dmit.service;

import com.dmit.dao.UserDao;
import com.dmit.dto.mapper.RoleDtoMapperImpl;
import com.dmit.dto.mapper.UserAuthenticationDtoMapper;
import com.dmit.dto.mapper.UserAuthenticationDtoMapperImpl;
import com.dmit.dto.mapper.UserDetailDtoMapperImpl;
import com.dmit.dto.user.UserAuthenticationDto;
import com.dmit.entity.user.User;
import com.dmit.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.Validator;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserAuthenticationServiceImplTest {
    @Spy
    private UserAuthenticationDtoMapper userAuthenticationDtoMapper = new UserAuthenticationDtoMapperImpl(new RoleDtoMapperImpl(), new UserDetailDtoMapperImpl());
    @Mock
    Validator validator;
    @Mock
    UserDao userDao;
    @InjectMocks
    UserAuthenticationServiceImpl targetObject;


    @Test
    public void findUserByUsernameShouldReturnUser() {
        // Given
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("User");

        // When
        when(userDao.findByUsername(user.getUsername()))
                .thenReturn(user);
        UserAuthenticationDto returnedUser = targetObject.findUserByUsername(user.getUsername());

        // Then
        assertEquals(user.getId(), returnedUser.getId());
        assertEquals(user.getUsername(), returnedUser.getUsername());
    }

    @Test
    public void findUserByUsernameShouldThrow() {
        // Given
        String username = "User";

        // When
        when(userDao.findByUsername(username))
                .thenReturn(null);

        // Then
        assertThrows(NotFoundException.class, () -> targetObject.findUserByUsername(username));
    }
}