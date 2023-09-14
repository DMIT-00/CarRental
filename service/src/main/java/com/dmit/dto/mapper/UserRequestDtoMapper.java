package com.dmit.dto.mapper;

import com.dmit.dto.user.UserRequestDto;
import com.dmit.entity.user.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = UserDetailDtoMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserRequestDtoMapper {
    UserRequestDto toDto(User user);

    User fromDto(UserRequestDto userRequestDto);
}
