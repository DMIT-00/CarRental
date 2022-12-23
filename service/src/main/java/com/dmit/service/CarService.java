package com.dmit.service;

import com.dmit.dao.CarDao;
import com.dmit.entity.car.Car;
import com.dmit.entity.car.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CarService {
    @Autowired
    CarDao carDao;

    @Transactional
    public List<Car> getAllCars() {
        return carDao.findAll();
    }

    @Transactional
    public void addNewCar(Car car) {
        //TODO: checks
        car.setId(null);
        carDao.create(car);
    }

    // TODO: security check
    @Transactional
    public void updateCarImages(UUID carId, List<Image> images) {
        Car car = carDao.findById(carId);
        if (car == null)
            throw new IllegalArgumentException("Car doesn't exist"); // TODO: custom exception

        car.setImages(images);
        carDao.update(car);
    }
}
