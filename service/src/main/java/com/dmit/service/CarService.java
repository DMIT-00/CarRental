package com.dmit.service;

import com.dmit.dao.CarDao;
import com.dmit.dto.car.CarDto;
import com.dmit.entity.car.Car;
import com.dmit.entity.car.Image;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CarDto> getAllCars() {
        return carDao.findAll().stream()
                .map(car -> modelMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void addNewCar(CarDto carDto) {
        Set<ConstraintViolation<CarDto>> violations = validator.validate(carDto);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<CarDto> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }

        Car car = modelMapper.map(carDto, Car.class);
        car.setId(null); // In case we get input with id somehow

        carDao.save(car);

        carDto.setId(car.getId()); // TODO: IS it ok?
    }

    @Transactional
    public CarDto getCar(UUID carId) {
        Car car = carDao.findById(carId).orElseThrow(); // TODO: custom exception

        return modelMapper.map(car, CarDto.class);
    }

    // TODO: security check
    @Transactional
    public void updateCarImages(UUID carId, List<byte[]> images) {
        Car car = carDao.findById(carId)
                .orElseThrow(); // TODO: custom exception

        car.setImages(images.stream()
                .map(image -> new Image(null, image))
                .collect(Collectors.toList()));

        carDao.save(car);
    }
}
