package com.dmit.service;

import com.dmit.dao.CarDao;
import com.dmit.dto.car.CarDto;
import com.dmit.dto.mapper.CarDtoMapper;
import com.dmit.entity.car.Car;
import com.dmit.entity.car.Image;
import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CarServiceImpl implements CarService {
    private final Validator validator;
    private final CarDtoMapper carDtoMapper;
    private final CarDao carDao;

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public CarDto addCar(CarDto newCar) {
        Set<ConstraintViolation<CarDto>> violations = validator.validate(newCar);

        if (!violations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<CarDto> constraintViolation : violations) {

                errors.append(constraintViolation.getPropertyPath())
                        .append(" ")
                        .append(constraintViolation.getMessage())
                        .append("; ");
            }
            throw new ConstraintViolationException("Validation errors: " + errors, violations);
        }

        Car car = carDtoMapper.fromDto(newCar);

        // Check for duplicate Id
        if (car.getId() != null && carDao.existsById(car.getId())) {
            throw new AlreadyExistsException("Car already exists! Id: " + car.getId());
        }

        return carDtoMapper.toDto(carDao.save(car));
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public CarDto updateCar(CarDto updatedCar) {
        Set<ConstraintViolation<CarDto>> violations = validator.validate(updatedCar);

        if (!violations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<CarDto> constraintViolation : violations) {
                errors.append(constraintViolation.getPropertyPath())
                        .append(" ")
                        .append(constraintViolation.getMessage())
                        .append("; ");
            }
            throw new ConstraintViolationException("Validation errors: " + errors, violations);
        }

        if (!carDao.existsById(updatedCar.getId()))
            throw new NotFoundException("Car not found! Id: " + updatedCar.getId());

        Car car = carDtoMapper.fromDto(updatedCar);

        return carDtoMapper.toDto(carDao.save(car));
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public void deleteCar(UUID id) {
        if (!carDao.existsById(id))
            throw new NotFoundException("Car not found! Id: " + id);

        carDao.deleteById(id);
    }

    @Override
    @Transactional
    public CarDto findCarById(UUID id) {
        Car car = carDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Car not found! Id: " + id));

        return carDtoMapper.toDto(car);
    }

    @Override
    @Transactional
    public long countAllCars() {
        return carDao.count();
    }

    @Override
    @Transactional
    public List<CarDto> findAllCarsPageable(int page, int size) {
        return carDao.findAll(PageRequest.of(page, size)).stream()
                .map(car -> carDtoMapper.toDto(car))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public void updateCarImages(UUID id, List<byte[]> images) {
        Car car = carDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Car not found! Id: " + id));

        car.setImages(images.stream()
                .map(image -> new Image(null, image))
                .collect(Collectors.toList()));

        carDao.save(car);
    }
}