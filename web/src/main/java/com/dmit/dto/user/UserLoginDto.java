package com.dmit.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginDto {
    @NotEmpty
    @NotNull
    @Email(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")
    private String email;
    @NotNull
    @Size(min = 4, max = 42)
    private String password;
}
