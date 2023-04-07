package com.dmit.service;

import com.dmit.dao.CarBrandDao;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.dto.mapper.CarBrandDtoMapper;
import com.dmit.entity.car.CarBrand;
import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BrandServiceImpl implements BrandService {
    private final ValidationService<CarBrandDto> validationService;
    private final CarBrandDtoMapper carBrandDtoMapper;
    private final CarBrandDao brandDao;

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public CarBrandDto addBrand(CarBrandDto newBrand) {
        validationService.validate(newBrand);

        CarBrand brand = carBrandDtoMapper.fromDto(newBrand);

        // Check for duplicate Id
        if (brand.getId() != null && brandDao.existsById(brand.getId())) {
            throw new AlreadyExistsException("Brand already exists! Id: " + brand.getId());
        }

        return carBrandDtoMapper.toDto(brandDao.save(brand));
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public CarBrandDto updateBrand(CarBrandDto updatedBrand) {
        validationService.validate(updatedBrand);

        CarBrand brand = brandDao.findById(updatedBrand.getId())
                .orElseThrow(() -> new NotFoundException("Brand not found! Id: " + updatedBrand.getId()));

        brand.setBrandName(updatedBrand.getBrandName());

        return carBrandDtoMapper.toDto(brandDao.save(brand));
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public void deleteBrand(Long id) {
        if (!brandDao.existsById(id))
            throw new NotFoundException("Brand not found! Id: " + id);

        brandDao.deleteById(id);
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public CarBrandDto findBrandById(Long id) {
        CarBrand brand = brandDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found! Id: " + id));

        return carBrandDtoMapper.toDto(brand);
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
                .map(carBrandDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}