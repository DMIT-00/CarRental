package com.dmit.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto implements GrantedAuthority {
    @NotNull
    @Size(min = 4, max = 20)
    private String roleName;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return "ROLE_" + roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
