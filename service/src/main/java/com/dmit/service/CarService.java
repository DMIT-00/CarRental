package com.dmit.service;

import com.dmit.dao.CarDao;
import com.dmit.entity.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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


}
