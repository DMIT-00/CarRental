package com.dmit.rest.car;

import com.dmit.dto.car.CarBrandDto;
import com.dmit.dto.car.CarDto;
import com.dmit.dto.car.CarModelDto;
import com.dmit.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/models")
public class ModelRestController {
    @Autowired
    ModelService modelService;

    @GetMapping
    public ResponseEntity<List<CarModelDto>> getModels() {
        // TODO: Pagination?
        List<CarModelDto> models = modelService.getAllModels();
        if (models.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<List<CarModelDto>> getModels(@PathVariable("brandId") long brandId) {
        // TODO: Pagination?
        List<CarModelDto> models = modelService.getAllBrandModels(brandId);
        if (models.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(models, HttpStatus.OK);
    }
}
