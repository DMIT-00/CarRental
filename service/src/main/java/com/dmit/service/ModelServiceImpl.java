package com.dmit.service;

import com.dmit.dao.CarModelDao;
import com.dmit.dto.car.CarModelDto;
import com.dmit.dto.mapper.CarBrandDtoMapper;
import com.dmit.dto.mapper.CarModelDtoMapper;
import com.dmit.entity.car.CarModel;
import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ModelServiceImpl implements ModelService {
    private final Validator validator;
    private final CarModelDtoMapper carModelDtoMapper;
    private final CarBrandDtoMapper carBrandDtoMapper;
    private final CarModelDao modelDao;

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public CarModelDto addModel(CarModelDto newModel) {
        Set<ConstraintViolation<CarModelDto>> violations = validator.validate(newModel);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<CarModelDto> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }

        CarModel model = carModelDtoMapper.fromDto(newModel);

        // Check for duplicate Id
        if (model.getId() != null && modelDao.existsById(model.getId())) {
            throw new AlreadyExistsException("Model already exists! Id: " + model.getId());
        }

        return carModelDtoMapper.toDto(modelDao.save(model));
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public CarModelDto updateModel(CarModelDto updatedModel) {
        CarModel model = modelDao.findById(updatedModel.getId())
                .orElseThrow(() -> new NotFoundException("Model not found! Id: " + updatedModel.getId()));

        model.setModelName(updatedModel.getModelName());
        model.setCarBrand(carBrandDtoMapper.fromDto(updatedModel.getCarBrand()));

        return carModelDtoMapper.toDto(modelDao.save(model));
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public void deleteModel(Long id) {
        if (!modelDao.existsById(id))
            throw new NotFoundException("Model not found! Id: " + id);

        modelDao.deleteById(id);
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public CarModelDto findModelById(Long id) {
        CarModel model = modelDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Model not found! Id: " + id));

        return carModelDtoMapper.toDto(model);
    }

    @Override
    @Transactional
    public long countAllModels() {
        return modelDao.count();
    }

    @Override
    @Transactional
    public List<CarModelDto> findAllModelsPageable(int page, int size) {
        return modelDao.findAll(PageRequest.of(page, size)).stream()
                .map(carModelDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public long countAllModelsByBrand(long brandId) {
        return modelDao.countByCarBrand_Id(brandId);
    }

    @Override
    @Transactional
    public List<CarModelDto> findAllModelsPageableByBrand(long brandId, int page, int size) {
        return modelDao.findAllByBrand(brandId, PageRequest.of(page, size)).stream()
                .map(carModelDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
