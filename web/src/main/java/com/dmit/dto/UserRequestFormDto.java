package com.dmit.dto;

import com.dmit.dto.constraints.PasswordsEqualConstraint;
import com.dmit.dto.user.UserDetailDto;
import com.dmit.dto.user.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@PasswordsEqualConstraint(message = "Passwords must be equal")
public class UserRequestFormDto extends UserRequestDto {
    @NotNull
    @Size(min = 4, max = 42)
    private String passwordRepeat;

    public UserRequestFormDto(UserDetailDto userDetailDto) {
        super(userDetailDto);
    }
}