package com.dmit.service;

import com.dmit.dto.car.CarModelDto;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface ModelService {
    @Secured("ROLE_MANAGER")
    CarModelDto addModel(CarModelDto newModel);

    @Secured("ROLE_MANAGER")
    CarModelDto updateModel(CarModelDto updatedModel);

    @Secured("ROLE_MANAGER")
    void deleteModel(Long id);

    @Secured("ROLE_MANAGER")
    CarModelDto findModelById(Long id);

    long countAllModels();

    List<CarModelDto> findAllModelsPageable(int page, int size);

    long countAllModelsByBrand(long brandId);

    List<CarModelDto> findAllModelsPageableByBrand(long brandId, int page, int size);
}
