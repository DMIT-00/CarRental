package com.dmit.service;

import com.dmit.dao.UserDao;
import com.dmit.dto.user.UserAuthenticationDto;
import com.dmit.entity.user.User;
import com.dmit.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.validation.Validator;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserAuthenticationServiceImplTest {
    @Spy
    ModelMapper modelMapper = new ModelMapper();
    @Mock
    Validator validator;
    @Mock
    UserDao userDao;
    @InjectMocks
    UserAuthenticationServiceImpl targetObject;

    public UserAuthenticationServiceImplTest() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

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