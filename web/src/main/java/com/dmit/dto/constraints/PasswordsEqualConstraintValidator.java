package com.dmit.dto.constraints;

import com.dmit.dto.UserRequestFormDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsEqualConstraintValidator implements
        ConstraintValidator<PasswordsEqualConstraint, UserRequestFormDto> {

    @Override
    public void initialize(PasswordsEqualConstraint arg0) {
    }

    @Override
    public boolean isValid(UserRequestFormDto userRequestDto, ConstraintValidatorContext arg1) {
        return userRequestDto.getPassword().equals(userRequestDto.getPasswordRepeat());
    }
}