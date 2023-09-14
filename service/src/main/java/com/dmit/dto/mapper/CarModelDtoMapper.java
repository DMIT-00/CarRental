package com.dmit.dto.mapper;

import com.dmit.dto.car.CarModelDto;
import com.dmit.entity.car.CarModel;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {CarBrandDtoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarModelDtoMapper {
    CarModelDto toDto(CarModel carModel);

    CarModel fromDto(CarModelDto carModelDto);
}
