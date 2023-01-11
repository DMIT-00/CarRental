package com.dmit.dto.user;

import com.dmit.dto.BaseDtoTest;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.entity.user.Role;
import com.dmit.entity.user.UserDetail;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDetailDtoTest extends BaseDtoTest {
    UserDetailDto targetObject;
    final int TARGET_CLASS_NUMBER_OF_FIELDS = 5;

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = modelMapper.map(userDetail, UserDetailDto.class);

        // Then
        try {
            modelMapper.validate();
        } catch (Exception e) {
            e.printStackTrace();
            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
        }

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
        targetObject = modelMapper.map(userDetail, UserDetailDto.class);

        // When
        UserDetail userDetailResult = modelMapper.map(targetObject, UserDetail.class);

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