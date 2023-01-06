package com.dmit.dto.constraints;

import com.dmit.dto.UserRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsEqualConstraintValidator implements
        ConstraintValidator<PasswordsEqualConstraint, UserRequestDto> {

    @Override
    public void initialize(PasswordsEqualConstraint arg0) {
    }

    @Override
    public boolean isValid(UserRequestDto userRequestDto, ConstraintValidatorContext arg1) {
        return userRequestDto.getPassword().equals(userRequestDto.getPasswordRepeat());
    }
}