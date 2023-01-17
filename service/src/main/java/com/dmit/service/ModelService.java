package com.dmit.service;

import com.dmit.dao.CarModelDao;
import com.dmit.dto.car.CarModelDto;
import com.dmit.entity.car.CarBrand;
import com.dmit.entity.car.CarModel;
import com.dmit.exception.AlreadyExistsException;
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
    CarModelDao modelDao;

    @Transactional
    public List<CarModelDto> getAllBrandModels(long brandId) {
        return modelDao.findAllByBrand(brandId).stream()
                .map(model -> modelMapper.map(model, CarModelDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CarModelDto> getAllModels() {
        return modelDao.findAll().stream()
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

        // Check for duplicate Id
        if (model.getId() != null && modelDao.findById(model.getId()).isPresent()) {
            throw new AlreadyExistsException("Model already exists! Id: " + model.getId());
        }

        modelDao.save(model);

        return modelMapper.map(model, CarModelDto.class);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public CarModelDto findModelById(Long id) {
        CarModel model = modelDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Model not found! Id: " + id));

        return modelMapper.map(model, CarModelDto.class);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public CarModelDto updateModel(CarModelDto updatedModel) {
        CarModel model = modelDao.findById(updatedModel.getId())
                .orElseThrow(() -> new NotFoundException("Model not found! Id: " + updatedModel.getId()));

        model.setModelName(updatedModel.getModelName());
        model.setCarBrand(modelMapper.map(updatedModel.getCarBrand(), CarBrand.class));

        return modelMapper.map(modelDao.save(model), CarModelDto.class);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public void deleteModel(Long id) {
        CarModel model = modelDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Model not found! Id: " + id));

        modelDao.delete(model);
    }
}
