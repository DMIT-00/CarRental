package com.dmit.service;

import com.dmit.dto.car.CarDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;
import java.util.UUID;

public interface CarService {
    @Secured("ROLE_MANAGER")
    CarDto addCar(CarDto newCar);

    @Secured("ROLE_MANAGER")
    CarDto updateCar(CarDto updatedCar);

    @Secured("ROLE_MANAGER")
    void deleteCar(UUID id);

    CarDto findCarById(UUID id);

    long countAllCars();

    List<CarDto> findAllCarsPageable(int page, int size);

    @Secured("ROLE_MANAGER")
    void updateCarImages(UUID id, List<byte[]> images);
}
