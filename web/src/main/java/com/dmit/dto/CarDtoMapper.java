package com.dmit.dto;

import com.dmit.entity.car.Car;
import com.dmit.entity.car.CarBrand;
import com.dmit.entity.car.CarModel;

import java.util.ArrayList;

public class CarDtoMapper {
    static public CarDto toDto(Car car) {
        if (car == null)
            return null;

        return new CarDto(
                car.getId(), car.getYear(), car.getColor(),
                car.getEnginePower(), car.getFuelType(), car.getFuelConsumption(),
                car.getTransmission(), car.getNumberOfSeats(),
                car.isAbs(), car.isCruiseControl(), car.isHeatedSeats(),
                car.isClimateControl(), car.isAirBags(), car.getPrice(),
                car.getBodyType(), car.getCarBrand().getId(), car.getCarBrand().getBrandName(),
                car.getCarModel().getId(), car.getCarModel().getModelName());
    }

    static public Car fromDto(CarDto carDto) {
        if (carDto == null)
            return null;

        CarBrand carBrand = new CarBrand(carDto.getBrandId(), carDto.getBrandName(), new ArrayList<>());
        return new Car(
                carDto.getId(), carDto.getYear(), carDto.getColor(), carDto.getEnginePower(),
                carDto.getFuelType(), carDto.getFuelConsumption(), carDto.getTransmission(),
                carDto.getNumberOfSeats(), carDto.isAbs(), carDto.isCruiseControl(),
                carDto.isHeatedSeats(), carDto.isClimateControl(), carDto.isAirBags(),
                carDto.getPrice(), carDto.getBodyType(),
                carBrand, new CarModel(carDto.getModelId(), carDto.getModelName(), carBrand), new ArrayList<>());
    }
}
