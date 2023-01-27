package com.dmit.dto.user;

import com.dmit.dto.BaseDtoTest;
import com.dmit.dto.mapper.RoleDtoMapperImpl;
import com.dmit.dto.mapper.UserAuthenticationDtoMapper;
import com.dmit.dto.mapper.UserAuthenticationDtoMapperImpl;
import com.dmit.dto.mapper.UserDetailDtoMapperImpl;
import com.dmit.entity.user.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserAuthenticationDtoTest extends BaseDtoTest {
    UserAuthenticationDto targetObject;
    UserAuthenticationDtoMapper mapper = new UserAuthenticationDtoMapperImpl(new RoleDtoMapperImpl(), new UserDetailDtoMapperImpl());
    final int TARGET_CLASS_NUMBER_OF_FIELDS = 7;

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = mapper.toDto(user);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), user.getId());
        assertEquals(targetObject.getUsername(), user.getUsername());
        assertEquals(targetObject.getEmail(), user.getEmail());
        assertEquals(targetObject.getPassword(), user.getPassword());
        assertEquals(targetObject.isLocked(), user.isLocked());

        assertEquals(targetObject.getRoles().size(), user.getRoles().size());
        // TODO: Check roles names

        assertEquals(targetObject.getUserDetail().getFirstName(), user.getUserDetail().getFirstName());
        assertEquals(targetObject.getUserDetail().getLastName(), user.getUserDetail().getLastName());
        assertEquals(targetObject.getUserDetail().getPhoneNumber(), user.getUserDetail().getPhoneNumber());
        assertEquals(targetObject.getUserDetail().getCreditCard(), user.getUserDetail().getCreditCard());
        assertEquals(targetObject.getUserDetail().getBirthDate(), user.getUserDetail().getBirthDate());
        // TODO: more assertions
    }

    @Test
    public void mappingFromDto() {
        // Given
        targetObject = mapper.toDto(user); // TODO: don't use mapping, so we don't fail when toDto fails

        // When
        User userResult = mapper.fromDto(targetObject);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(userResult.getId(), user.getId());
        assertEquals(userResult.getUsername(), user.getUsername());
        assertEquals(userResult.getEmail(), user.getEmail());
        assertEquals(userResult.getPassword(), user.getPassword());
        assertEquals(userResult.isLocked(), user.isLocked());

        assertEquals(userResult.getRoles().size(), user.getRoles().size());
        // TODO: Check roles names

        assertEquals(userResult.getUserDetail().getFirstName(), user.getUserDetail().getFirstName());
        assertEquals(userResult.getUserDetail().getLastName(), user.getUserDetail().getLastName());
        assertEquals(userResult.getUserDetail().getPhoneNumber(), user.getUserDetail().getPhoneNumber());
        assertEquals(userResult.getUserDetail().getCreditCard(), user.getUserDetail().getCreditCard());
        assertEquals(userResult.getUserDetail().getBirthDate(), user.getUserDetail().getBirthDate());
        // TODO: more assertions
    }
}