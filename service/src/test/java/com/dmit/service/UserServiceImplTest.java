package com.dmit.service;

import com.dmit.dao.UserDao;
import com.dmit.dto.user.UserDetailDto;
import com.dmit.dto.user.UserRequestDto;
import com.dmit.exception.AlreadyExistsException;
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

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Spy
    ModelMapper modelMapper = new ModelMapper();
    @Mock
    Validator validator;
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

    // TODO: more tests
}