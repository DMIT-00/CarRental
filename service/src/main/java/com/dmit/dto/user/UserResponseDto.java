package com.dmit.dto.user;

import com.dmit.dto.order.OrderIdDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private UUID id;
    @NotEmpty
    @NotNull
    @Email(regexp = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$")
    private String email;
    @NotNull
    @Size(min = 4, max = 20)
    private String username;
    private boolean locked;
    @Valid
    @NotNull
    private UserDetailDto userDetail;
    @Valid
    private List<OrderIdDto> orders;
    @Valid
    @NotNull
    private Set<RoleDto> roles = new HashSet<>();

    public UserResponseDto(UserDetailDto userDetailDto) {
        this.userDetail = userDetailDto;
    }
}
