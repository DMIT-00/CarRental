package com.dmit.dto;

import com.dmit.entity.car.CarBrand;

import java.util.ArrayList;

public class BrandDtoMapper {
    static public BrandDto toDto(CarBrand carBrand) {
        return new BrandDto(carBrand.getId(), carBrand.getBrandName());
    }

    static public CarBrand fromDto(BrandDto brandDto) {
        return new CarBrand(brandDto.getId(), brandDto.getBrandName(), new ArrayList<>());
    }
}
