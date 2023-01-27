package com.dmit.dto.user;

import com.dmit.dto.BaseDtoTest;
import com.dmit.dto.mapper.UserDetailDtoMapper;
import com.dmit.dto.mapper.UserDetailDtoMapperImpl;
import com.dmit.entity.user.UserDetail;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDetailDtoTest extends BaseDtoTest {
    UserDetailDto targetObject;
    UserDetailDtoMapper mapper = new UserDetailDtoMapperImpl();
    final int TARGET_CLASS_NUMBER_OF_FIELDS = 5;

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = mapper.toDto(userDetail);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(targetObject.getBirthDate(), userDetail.getBirthDate());
        assertEquals(targetObject.getPhoneNumber(), userDetail.getPhoneNumber());
        assertEquals(targetObject.getCreditCard(), userDetail.getCreditCard());
        assertEquals(targetObject.getLastName(), userDetail.getLastName());
        assertEquals(targetObject.getFirstName(), userDetail.getFirstName());
    }

    @Test
    public void mappingFromDto() {
        // Given
        targetObject = mapper.toDto(userDetail); // TODO: don't use mapping, so we don't fail when toDto fails

        // When
        UserDetail userDetailResult = mapper.fromDto(targetObject);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(userDetailResult.getBirthDate(), userDetail.getBirthDate());
        assertEquals(userDetailResult.getPhoneNumber(), userDetail.getPhoneNumber());
        assertEquals(userDetailResult.getCreditCard(), userDetail.getCreditCard());
        assertEquals(userDetailResult.getLastName(), userDetail.getLastName());
        assertEquals(userDetailResult.getFirstName(), userDetail.getFirstName());
    }
}