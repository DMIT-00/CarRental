package com.dmit.dto.mapper;

import com.dmit.dto.user.RoleDto;
import com.dmit.entity.user.Role;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoleDtoMapper {
    RoleDto toDto(Role role);
    Role fromDto(RoleDto roleDto);

    Set<RoleDto> toDto(Set<Role> role);
    Set<Role> fromDto(Set<RoleDto> roleDto);
}
