package com.dmit.service;

import com.dmit.dao.CarBrandDao;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.entity.car.CarBrand;
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
public class BrandService {
    @Autowired
    private Validator validator;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CarBrandDao brandDao;


    @Transactional
    public List<CarBrandDto> getAllBrands() {
        return brandDao.findAll().stream()
                .map(brand -> modelMapper.map(brand, CarBrandDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public CarBrandDto addNewBrand(CarBrandDto newBrand) {
        Set<ConstraintViolation<CarBrandDto>> violations = validator.validate(newBrand);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<CarBrandDto> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }

        CarBrand brand = modelMapper.map(newBrand, CarBrand.class);

        // Check for duplicate Id
        if (brand.getId() != null && brandDao.findById(brand.getId()).isPresent()) {
            throw new AlreadyExistsException("Brand already exists! Id: " + brand.getId());
        }

        brandDao.save(brand);

        return modelMapper.map(brand, CarBrandDto.class);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public CarBrandDto findBrandById(Long id) {
        CarBrand brand = brandDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found! Id: " + id));

        return modelMapper.map(brand, CarBrandDto.class);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public CarBrandDto updateBrand(CarBrandDto updatedBrand) {
        CarBrand brand = brandDao.findById(updatedBrand.getId())
                .orElseThrow(() -> new NotFoundException("Brand not found! Id: " + updatedBrand.getId()));

        brand.setBrandName(updatedBrand.getBrandName());

        return modelMapper.map(brandDao.save(brand), CarBrandDto.class);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public void deleteBrand(Long id) {
        CarBrand brand = brandDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found! Id: " + id));

        brandDao.delete(brand);
    }
}
