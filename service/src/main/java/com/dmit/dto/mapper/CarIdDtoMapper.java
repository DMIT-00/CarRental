package com.dmit.dto.mapper;

import com.dmit.dto.car.CarIdDto;
import com.dmit.entity.car.Car;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarIdDtoMapper {
    CarIdDto toDto(Car car);

    Car fromDto(CarIdDto carIdDto);
}
