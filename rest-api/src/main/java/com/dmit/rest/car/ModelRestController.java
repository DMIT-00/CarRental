package com.dmit.rest.car;

import com.dmit.dto.car.CarBrandDto;
import com.dmit.dto.car.CarDto;
import com.dmit.dto.car.CarModelDto;
import com.dmit.exception.NotFoundException;
import com.dmit.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/models")
public class ModelRestController {
    @Autowired
    ModelService modelService;

    @GetMapping
    public ResponseEntity<List<CarModelDto>> getModels(@RequestParam("page") int page,
                                                       @RequestParam("size") int size) {
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

    @GetMapping("/{id}")
    public ResponseEntity<CarModelDto> getModel(@PathVariable("id") Long id) {
        CarModelDto model;

        try {
            model = modelService.findModelById(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarModelDto> addModel(CarModelDto model) {
        CarModelDto addedModel = modelService.addNewModel(model);
        return new ResponseEntity<>(addedModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarModelDto> updateModel(@PathVariable("id") Long id, @RequestBody CarModelDto newModel) {
        CarModelDto model;

        try {
            model = modelService.findModelById(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        model.setModelName(newModel.getModelName());
        model.setCarBrand(newModel.getCarBrand());

        newModel = modelService.updateModel(model);

        return new ResponseEntity<>(newModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable("id") Long id) {
        try {
            modelService.deleteModel(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
