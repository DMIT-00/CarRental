package com.dmit.dto.user;

import com.dmit.dto.BaseDtoTest;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.dto.car.CarModelDto;
import com.dmit.entity.car.CarModel;
import com.dmit.entity.user.Role;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleDtoTest extends BaseDtoTest {
    RoleDto targetObject;
    final int TARGET_CLASS_NUMBER_OF_FIELDS = 1;

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = modelMapper.map(roleUser, RoleDto.class);

        // Then
        try {
            modelMapper.validate();
        } catch (Exception e) {
            e.printStackTrace();
            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
        }

        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(targetObject.getRoleName(), roleUser.getRoleName());
        assertEquals(targetObject.getAuthority(), "ROLE_" + roleUser.getRoleName());
    }

    @Test
    public void mappingFromDto() {
        // Given
        targetObject = modelMapper.map(roleUser, RoleDto.class);

        // When
        Role roleResult = modelMapper.map(targetObject, Role.class);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(roleResult.getRoleName(), roleUser.getRoleName());
    }
}