package com.dmit.dto;

import com.dmit.dto.constraints.PasswordsEqualConstraint;
import com.dmit.dto.user.UserDetailDto;
import com.dmit.dto.user.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@PasswordsEqualConstraint(message = "Passwords must be equal")
public class UserRequestDto extends UserDto {
    @NotNull
    @Size(min = 4, max = 42)
    private String passwordRepeat;

    public UserRequestDto(UserDetailDto userDetailDto) {
        super(userDetailDto);
    }
}