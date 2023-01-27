package com.dmit.dto.mapper;

import com.dmit.dto.user.UserIdDto;
import com.dmit.entity.user.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserIdDtoMapper {
    UserIdDto toDto(User user);
    User fromDto(UserIdDto userIdDto);
}
