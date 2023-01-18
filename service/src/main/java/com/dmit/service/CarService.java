package com.dmit.service;

import com.dmit.dao.CarDao;
import com.dmit.dto.car.CarDto;
import com.dmit.entity.car.Car;
import com.dmit.entity.car.Image;
import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.NotFoundException;
import org.modelmapper.ModelMapper;
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
public class CarService {
    @Autowired
    private Validator validator;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    CarDao carDao;

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

        Car car = modelMapper.map(newCar, Car.class);

        // Check for duplicate Id
        if (car.getId() != null && carDao.findById(car.getId()).isPresent()) {
            throw new AlreadyExistsException("Car already exists! Id: " + car.getId());
        }

        carDao.save(car);

        return modelMapper.map(car, CarDto.class);
    }

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

        Car car = modelMapper.map(updatedCar, Car.class);

        return modelMapper.map(carDao.save(car), CarDto.class);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public void deleteCar(UUID id) {
        if (!carDao.existsById(id))
            throw new NotFoundException("Car not found! Id: " + id);

        carDao.deleteById(id);
    }

    @Transactional
    public CarDto findCarById(UUID id) {
        Car car = carDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Car not found! Id: " + id));

        return modelMapper.map(car, CarDto.class);
    }

    @Transactional
    public long countAllCars() {
        return carDao.count();
    }

    @Transactional
    public List<CarDto> findAllCarsPageable(int page, int size) {
        return carDao.findAll(PageRequest.of(page, size)).stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
    }

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