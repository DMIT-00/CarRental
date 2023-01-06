package com.dmit.dto.user;

import com.dmit.dto.constraints.PasswordsEqualConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PasswordsEqualConstraint(message = "Passwords must be equal")
public class UserRequestDto {
    @NotEmpty
    @NotNull
    @Email(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")
    private String email;
    @NotNull
    @Size(min = 4, max = 20)
    private String username;
    @NotNull
    @Size(min = 4, max = 42)
    private String password;
    @NotNull
    @Size(min = 4, max = 42)
    private String passwordRepeat;
    @Valid
    @NotNull
    private UserDetailDto userDetail;

    public UserRequestDto(UserDetailDto userDetailDto) {
        this.userDetail = userDetailDto;
    }
}

// TODO: frontend class move