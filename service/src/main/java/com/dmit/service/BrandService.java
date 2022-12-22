package com.dmit.service;

import com.dmit.dao.CarBrandDao;
import com.dmit.entity.car.CarBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BrandService {
    @Autowired
    CarBrandDao carBrandDao;

    @Transactional
    public List<CarBrand> getAllBrands() {
        return carBrandDao.findAll();
    }

    @Transactional
    public void addNewBrand(CarBrand carBrand) {
        //TODO: checks
        carBrand.setId(null);
        carBrandDao.create(carBrand);
    }
}
