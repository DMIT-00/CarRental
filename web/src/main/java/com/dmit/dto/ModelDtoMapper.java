package com.dmit.dto;

import com.dmit.entity.car.CarBrand;
import com.dmit.entity.car.CarModel;

import java.util.ArrayList;

public class ModelDtoMapper {
    static public ModelDto toDto(CarModel carModel) {
        if (carModel == null)
            return null;
        return new ModelDto(carModel.getId(), carModel.getModelName(), carModel.getCarBrand().getId(), carModel.getCarBrand().getBrandName());
    }

    static public CarModel fromDto(ModelDto modelDto) {
        if (modelDto == null)
            return null;
        return new CarModel(modelDto.getId(), modelDto.getModelName(), new CarBrand(modelDto.getBrandId(),modelDto.getBrandName(), new ArrayList<>()));
    }
}
