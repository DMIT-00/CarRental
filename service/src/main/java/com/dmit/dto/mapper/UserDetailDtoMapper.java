package com.dmit.dto.mapper;

import com.dmit.dto.user.UserDetailDto;
import com.dmit.entity.user.UserDetail;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserDetailDtoMapper {
    UserDetailDto toDto(UserDetail userDetail);

    UserDetail fromDto(UserDetailDto userDetailDto);
}
