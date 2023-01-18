package com.dmit.service;

import com.dmit.dto.car.CarBrandDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface BrandService {
    @Secured("ROLE_MANAGER")
    CarBrandDto addBrand(CarBrandDto newBrand);
    @Secured("ROLE_MANAGER")
    CarBrandDto updateBrand(CarBrandDto updatedBrand);
    @Secured("ROLE_MANAGER")
    void deleteBrand(Long id);

    @Secured("ROLE_MANAGER")
    CarBrandDto findBrandById(Long id);
    long countAllBrands();
    List<CarBrandDto> findAllBrandsPageable(int page, int size);
}