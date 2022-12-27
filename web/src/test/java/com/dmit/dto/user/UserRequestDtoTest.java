package com.dmit.dto.user;

import com.dmit.config.DataConfig;
import com.dmit.configuration.WebConfiguration;
import com.dmit.service.config.ServiceConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {WebConfiguration.class, ServiceConfig.class, DataConfig.class})
//@WebAppConfiguration
//public class UserRequestDtoTest {
//    @Autowired
//    protected WebApplicationContext wac;
//    private Validator validator;
//
//    @Before
//    public void setUp() throws Exception {
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        validator = validatorFactory.getValidator();
//    }
//
//    @Test
//    public void shouldMarkPasswordsAsInvalid() throws Exception {
//        // Given
//        UserRequestDto userRequestDto = new UserRequestDto(
//                "aaaa@gmail.com", "Abram",
//                null, null,
//                new UserDetailDto(
//                        "Abram", "Damk",
//                        "+37544222222", "0000000000000000",
//                        Date.valueOf("2000-01-01")));
//
//        userRequestDto.setPassword("AAAAAAAA");
//        userRequestDto.setPasswordRepeat("BBBBBBBB");
//
//        // When
//        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
//
//        // Then
//        assertEquals(1, violations.size());
//    }
//
//    @Test
//    public void shouldMarkPasswordsAsValid() throws Exception {
//        // Given
//        UserRequestDto userRequestDto = new UserRequestDto(
//                "aaaa@gmail.com", "Abram",
//                null, null,
//                new UserDetailDto(
//                        "Abram", "Damk",
//                        "+37544222222", "0000000000000000",
//                        Date.valueOf("2000-01-01")));
//        userRequestDto.setPassword("AAAAAAAA");
//        userRequestDto.setPasswordRepeat("AAAAAAAA");
//
//        // When
//        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
//
//        // Then
//        assertEquals(0, violations.size());
//    }
//}
