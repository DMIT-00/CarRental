package com.dmit.dto.mapper;

import com.dmit.dto.user.UserResponseDto;
import com.dmit.entity.user.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {RoleDtoMapper.class, UserDetailDtoMapper.class, OrderDtoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserResponseDtoMapper {
    UserResponseDto toDto(User user);
    User fromDto(UserResponseDto userResponseDto);
}
