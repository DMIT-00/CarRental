package com.dmit.service;

import com.dmit.dao.CarModelDao;
import com.dmit.dto.car.CarModelDto;
import com.dmit.entity.car.CarModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addNewModel(CarModelDto carModelDto) {
        Set<ConstraintViolation<CarModelDto>> violations = validator.validate(carModelDto);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<CarModelDto> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }

        CarModel carModel = modelMapper.map(carModelDto, CarModel.class);
        carModel.setId(null); // In case we get input with id somehow

        carModelDao.save(carModel);
    }

}
