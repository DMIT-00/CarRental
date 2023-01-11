package com.dmit.dto;

import com.dmit.dto.user.UserDetailDto;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;
public class UserRequestDtoTest {
    private final Validator validator;

    public UserRequestDtoTest() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        validatorFactory.close();
    }

    @Test
    public void shouldMarkPasswordsAsInvalid() throws Exception {
        // Given
        UserRequestDto userRequestDto = new UserRequestDto(new UserDetailDto(
                "First Name", "Last Name", "+37544222222",
                "0000000000000000", Date.valueOf("2000-01-01")));
        userRequestDto.setEmail("aaaa@gmail.com");
        userRequestDto.setUsername("Abram");

        userRequestDto.setPassword("AAAAAAAA");
        userRequestDto.setPasswordRepeat("BBBBBBBB");

        // When
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);

        // Then
        assertEquals(1, violations.size());
    }

    @Test
    public void shouldMarkPasswordsAsValid() throws Exception {
        // Given
        UserRequestDto userRequestDto = new UserRequestDto(new UserDetailDto(
                "First Name", "Last Name", "+37544222222",
                "0000000000000000", Date.valueOf("2000-01-01")));
        userRequestDto.setEmail("aaaa@gmail.com");
        userRequestDto.setUsername("Abram");

        userRequestDto.setPassword("AAAAAAAA");
        userRequestDto.setPasswordRepeat("AAAAAAAA");

        // When
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);

        // Then
        assertEquals(0, violations.size());
    }
}