package com.dmit.dto.mapper;

import com.dmit.dto.user.UserAuthenticationDto;
import com.dmit.entity.user.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {RoleDtoMapper.class, UserDetailDtoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserAuthenticationDtoMapper {
    UserAuthenticationDto toDto(User user);
    User fromDto(UserAuthenticationDto userAuthenticationDto);
}
