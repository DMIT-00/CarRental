package com.dmit.dto.user;

import com.dmit.dto.BaseDtoTest;
import com.dmit.dto.mapper.RoleDtoMapper;
import com.dmit.dto.mapper.RoleDtoMapperImpl;
import com.dmit.entity.user.Role;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoleDtoTest extends BaseDtoTest {
    private final int TARGET_CLASS_NUMBER_OF_FIELDS = 1;
    private RoleDto targetObject;
    private RoleDtoMapper mapper = new RoleDtoMapperImpl();

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = mapper.toDto(roleUser);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(targetObject.getRoleName(), roleUser.getRoleName());
        assertEquals(targetObject.getAuthority(), "ROLE_" + roleUser.getRoleName());
    }

    @Test
    public void mappingFromDto() {
        // Given
        targetObject = mapper.toDto(roleUser); // TODO: don't use mapping, so we don't fail when toDto fails

        // When
        Role roleResult = mapper.fromDto(targetObject);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(roleResult.getRoleName(), roleUser.getRoleName());
    }
}