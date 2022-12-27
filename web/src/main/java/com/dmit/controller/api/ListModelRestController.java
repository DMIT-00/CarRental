package com.dmit.controller.api;

import com.dmit.dto.car.CarModelDto;
import com.dmit.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ListModelRestController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ModelService modelService;

    @GetMapping({
            "/api/v1/get_all_models",
            "/api/v1/get_all_models/{brandId}"
    })
    public List<CarModelDto> getAllModels(@PathVariable(required = false) Long brandId) {
        if (brandId != null) {
            return modelService.getAllBrandModels(brandId).stream()
                    .map(model -> modelMapper.map(model, CarModelDto.class))
                    .collect(Collectors.toList());
        }
        return modelService.getAllModels().stream()
                .map(model -> modelMapper.map(model, CarModelDto.class))
                .collect(Collectors.toList());
    }
}
