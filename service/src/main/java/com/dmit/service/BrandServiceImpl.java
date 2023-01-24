package com.dmit.service;

import com.dmit.dao.CarBrandDao;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.entity.car.CarBrand;
import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
public class BrandServiceImpl implements BrandService {
    @Autowired
    private Validator validator;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CarBrandDao brandDao;

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public CarBrandDto addBrand(CarBrandDto newBrand) {
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
        if (brand.getId() != null && brandDao.existsById(brand.getId())) {
            throw new AlreadyExistsException("Brand already exists! Id: " + brand.getId());
        }

        return modelMapper.map(brandDao.save(brand), CarBrandDto.class);
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public CarBrandDto updateBrand(CarBrandDto updatedBrand) {
        Set<ConstraintViolation<CarBrandDto>> violations = validator.validate(updatedBrand);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<CarBrandDto> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }

        CarBrand brand = brandDao.findById(updatedBrand.getId())
                .orElseThrow(() -> new NotFoundException("Brand not found! Id: " + updatedBrand.getId()));

        brand.setBrandName(updatedBrand.getBrandName());

        return modelMapper.map(brandDao.save(brand), CarBrandDto.class);
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public void deleteBrand(Long id) {
        CarBrand brand = brandDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found! Id: " + id));

        brandDao.delete(brand);
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public CarBrandDto findBrandById(Long id) {
        CarBrand brand = brandDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found! Id: " + id));

        return modelMapper.map(brand, CarBrandDto.class);
    }

    @Override
    @Transactional
    public long countAllBrands() {
        return brandDao.count();
    }

    @Override
    @Transactional
    public List<CarBrandDto> findAllBrandsPageable(int page, int size) {
        return brandDao.findAll(PageRequest.of(page, size)).stream()
                .map(brand -> modelMapper.map(brand, CarBrandDto.class))
                .collect(Collectors.toList());
    }
}