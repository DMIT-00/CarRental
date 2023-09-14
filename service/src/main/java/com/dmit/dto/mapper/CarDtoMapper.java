package com.dmit.dto.mapper;

import com.dmit.dto.car.CarDto;
import com.dmit.entity.car.Car;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                CarModelDtoMapper.class,
                OrderIdDtoMapper.class
        },
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarDtoMapper {
    CarDto toDto(Car car);

    Car fromDto(CarDto carDto);
}
