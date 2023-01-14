package com.dmit.service;

import com.dmit.dao.CarModelDao;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.dto.car.CarModelDto;
import com.dmit.entity.car.CarBrand;
import com.dmit.entity.car.CarModel;
import com.dmit.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ModelService {
    @Autowired
    private Validator validator;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    CarModelDao carModelDao;

    @Transactional
    public List<CarModelDto> getAllBrandModels(long brandId) {
        return carModelDao.findAllByBrand(brandId).stream()
                .map(model -> modelMapper.map(model, CarModelDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CarModelDto> getAllModels() {
        return carModelDao.findAll().stream()
                .map(model -> modelMapper.map(model, CarModelDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public CarModelDto addNewModel(CarModelDto newModel) {
        Set<ConstraintViolation<CarModelDto>> violations = validator.validate(newModel);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<CarModelDto> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }

        CarModel model = modelMapper.map(newModel, CarModel.class);
        model.setId(null); // In case we get input with id somehow

        carModelDao.save(model);

        return modelMapper.map(model, CarModelDto.class);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public CarModelDto findModelById(Long id) {
        CarModel model = carModelDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Model not found! Id: " + id));

        return modelMapper.map(model, CarModelDto.class);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public CarModelDto updateModel(CarModelDto updatedModel) {
        CarModel model = carModelDao.findById(updatedModel.getId())
                .orElseThrow(() -> new NotFoundException("Model not found! Id: " + updatedModel.getId()));

        model.setModelName(updatedModel.getModelName());
        model.setCarBrand(modelMapper.map(updatedModel.getCarBrand(), CarBrand.class));

        return modelMapper.map(carModelDao.save(model), CarModelDto.class);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public void deleteModel(Long id) {
        CarModel model = carModelDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Model not found! Id: " + id));

        carModelDao.delete(model);
    }
}
