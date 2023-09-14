package com.dmit.dto.mapper;

import com.dmit.dto.car.CarBrandDto;
import com.dmit.entity.car.CarBrand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarBrandDtoMapper {
    CarBrandDto toDto(CarBrand carBrand);

    CarBrand fromDto(CarBrandDto carBrandDto);
}
