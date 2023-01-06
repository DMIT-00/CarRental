package com.dmit.service;

import com.dmit.dao.CarBrandDao;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.entity.car.CarBrand;
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
public class BrandService {
    @Autowired
    private Validator validator;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CarBrandDao carBrandDao;


    @Transactional
    public List<CarBrandDto> getAllBrands() {
        return carBrandDao.findAll().stream()
                .map(brand -> modelMapper.map(brand, CarBrandDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void addNewBrand(CarBrandDto carBrandDto) {
        Set<ConstraintViolation<CarBrandDto>> violations = validator.validate(carBrandDto);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<CarBrandDto> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }

            CarBrand carBrand = modelMapper.map(carBrandDto, CarBrand.class);
        carBrand.setId(null); // In case we get input with id somehow

        carBrandDao.save(carBrand);
    }
}
