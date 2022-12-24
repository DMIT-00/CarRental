package com.dmit.service;

import com.dmit.dao.CarModelDao;
import com.dmit.entity.car.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ModelService {
    @Autowired
    CarModelDao carModelDao;

    @Transactional
    public List<CarModel> getAllBrandModels(long brandId) {
        return carModelDao.findAllByBrand(brandId);
    }

    @Transactional
    public List<CarModel> getAllModels() {
        return carModelDao.findAll();
    }

    @Transactional
    public void addNewModel(CarModel carModel) {
        //TODO: checks
        carModel.setId(null);
        carModelDao.create(carModel);
    }

}
