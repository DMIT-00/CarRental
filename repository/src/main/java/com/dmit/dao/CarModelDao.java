package com.dmit.dao;

import com.dmit.entity.car.CarModel;

import java.util.List;

public interface CarModelDao extends BaseDao<CarModel, Long> {
    List<CarModel> findAllByBrand(long brandId);
}
