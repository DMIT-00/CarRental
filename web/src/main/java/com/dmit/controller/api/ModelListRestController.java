package com.dmit.controller.api;

import com.dmit.dto.ModelDto;
import com.dmit.dto.ModelDtoMapper;
import com.dmit.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ModelListRestController {
    @Autowired
    ModelService modelService;

    @GetMapping({
            "/api/v1/get_all_models",
            "/api/v1/get_all_models/{brand_id}"
    })
    public List<ModelDto> getAllModels(@PathVariable(required = false) Long brand_id) {
        if (brand_id != null) {
            return modelService.getAllBrandModels(brand_id).stream()
                    .map(ModelDtoMapper::toDto).collect(Collectors.toList());
        }
        return modelService.getAllModels().stream()
                .map(ModelDtoMapper::toDto).collect(Collectors.toList());
    }
}
