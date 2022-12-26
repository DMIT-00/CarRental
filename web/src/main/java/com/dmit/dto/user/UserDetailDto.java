package com.dmit.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDto {
    @NotNull
    @Size(min = 1, max = 20)
    private String firstName;
    @NotNull
    @Size(min = 1, max = 20)
    private String lastName;
    @NotNull
    @Size(min = 10, max = 14)
    @Pattern(regexp="\\+[1-9][0-9]{0,2}[0-9]{2,3}[0-9]{5}")
    private String phoneNumber;
    @NotNull
    @Size(min = 16, max = 16)
    @Pattern(regexp="[0-9]{16}")
    private String creditCard;
    @Past
    @NotNull
    private Date birthDate;
}
