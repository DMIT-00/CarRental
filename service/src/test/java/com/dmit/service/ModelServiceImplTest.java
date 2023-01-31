package com.dmit.service;

import com.dmit.dao.CarModelDao;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.dto.car.CarModelDto;
import com.dmit.dto.mapper.CarBrandDtoMapper;
import com.dmit.dto.mapper.CarBrandDtoMapperImpl;
import com.dmit.dto.mapper.CarModelDtoMapper;
import com.dmit.dto.mapper.CarModelDtoMapperImpl;
import com.dmit.entity.car.CarBrand;
import com.dmit.entity.car.CarModel;
import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ModelServiceImplTest {
    @Spy
    private CarModelDtoMapper carModelDtoMapper = new CarModelDtoMapperImpl(new CarBrandDtoMapperImpl());
    @Spy
    private CarBrandDtoMapper carBrandDtoMapper = new CarBrandDtoMapperImpl();

    @Mock
    Validator validator;
    @Mock
    CarModelDao modelDao;
    @InjectMocks
    ModelServiceImpl targetObject;


    @Test
    public void addModelShouldCallDao() {
        // Given
        CarModelDto carModelDto = new CarModelDto(1L, "X1", new CarBrandDto(1L, "BMW"));

        // When
        when(modelDao.save(any())).thenReturn(new CarModel());
        targetObject.addModel(carModelDto);

        // Then
        ArgumentCaptor<CarModel> argument = ArgumentCaptor.forClass(CarModel.class);
        verify(modelDao).save(argument.capture());
        assertEquals(argument.getValue().getModelName(), carModelDto.getModelName());
        assertEquals(argument.getValue().getId(), carModelDto.getId());
        assertEquals(argument.getValue().getCarBrand().getId(), carModelDto.getCarBrand().getId());
        assertEquals(argument.getValue().getCarBrand().getBrandName(), carModelDto.getCarBrand().getBrandName());
    }

    @Test
    public void addModelShouldThrowOnDuplicateId() {
        // Given
        CarModelDto carModelDto = new CarModelDto();
        carModelDto.setId(1L);

        // When
        when(modelDao.existsById(carModelDto.getId()))
                .thenReturn(true);

        // Then
        Exception exception = assertThrows(AlreadyExistsException.class, () -> targetObject.addModel(carModelDto));
        assertEquals(exception.getMessage(), "Model already exists! Id: " + carModelDto.getId());
    }

    @Test
    public void updateModelShouldThrowWhenModelDoesNotExist() {
        // Given
        CarModelDto carModelDto = new CarModelDto();
        carModelDto.setId(1L);

        // When
        // Mockito will return false for modelDao.existsById(carModelDto.getId()) by default

        // Then
        Exception exception = assertThrows(NotFoundException.class, () -> targetObject.updateModel(carModelDto));
        assertEquals(exception.getMessage(), "Model not found! Id: " + carModelDto.getId());
    }

    @Test
    public void updateModelShouldCallDao() {
        // Given
        CarModelDto carModelDto = new CarModelDto(1L, "X1", new CarBrandDto(1L, "BMW"));

        // When
        when(modelDao.findById(carModelDto.getId()))
                .thenReturn(Optional.of(new CarModel(
                        carModelDto.getId(),
                        carModelDto.getModelName(),
                        new CarBrand(
                                carModelDto.getCarBrand().getId(),
                                carModelDto.getCarBrand().getBrandName(),
                                new ArrayList<>()
                        ))
                ));
        targetObject.updateModel(carModelDto);

        // Then
        ArgumentCaptor<CarModel> argument = ArgumentCaptor.forClass(CarModel.class);
        verify(modelDao).save(argument.capture());
        assertEquals(argument.getValue().getModelName(), carModelDto.getModelName());
        assertEquals(argument.getValue().getId(), carModelDto.getId());
        assertEquals(argument.getValue().getCarBrand().getId(), carModelDto.getCarBrand().getId());
        assertEquals(argument.getValue().getCarBrand().getBrandName(), carModelDto.getCarBrand().getBrandName());
    }

    @Test
    public void deleteModelShouldThrowWhenModelDoesNotExist() {
        // Given
        CarModelDto carModelDto = new CarModelDto();
        carModelDto.setId(1L);

        // When
        when(modelDao.existsById(carModelDto.getId()))
                .thenReturn(false);

        // Then
        Exception exception = assertThrows(NotFoundException.class, () -> targetObject.deleteModel(carModelDto.getId()));
        assertEquals(exception.getMessage(), "Model not found! Id: " + carModelDto.getId());
    }

    @Test
    public void deleteModelShouldCallDao() {
        // Given
        CarModelDto carModelDto = new CarModelDto();
        carModelDto.setId(1L);

        // When
        when(modelDao.existsById(carModelDto.getId()))
                .thenReturn(true);
        targetObject.deleteModel(carModelDto.getId());

        // Then
        ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
        verify(modelDao).deleteById(argument.capture());
        assertEquals(argument.getValue(), carModelDto.getId());
    }

    @Test
    public void findModelByIdShouldThrowWhenModelDoesNotExist() {
        // Given
        CarModelDto carModelDto = new CarModelDto();
        carModelDto.setId(1L);

        // When
        when(modelDao.findById(carModelDto.getId()))
                .thenReturn(Optional.empty());

        // Then
        Exception exception = assertThrows(NotFoundException.class, () -> targetObject.findModelById(carModelDto.getId()));
        assertEquals(exception.getMessage(), "Model not found! Id: " + carModelDto.getId());
    }

    @Test
    public void findModelByIdShouldReturnModel() {
        // Given
        CarModelDto carModelDto = new CarModelDto(1L, "X1", new CarBrandDto(1L, "BMW"));

        // When
        when(modelDao.findById(carModelDto.getId()))
                .thenReturn(Optional.of(new CarModel(
                        carModelDto.getId(),
                        carModelDto.getModelName(),
                        new CarBrand(
                                carModelDto.getCarBrand().getId(),
                                carModelDto.getCarBrand().getBrandName(),
                                new ArrayList<>()
                        ))
                ));
        CarModelDto returnedCarModelDto = targetObject.findModelById(carModelDto.getId());

        // Then
        assertEquals(carModelDto.getId(), returnedCarModelDto.getId());
        assertEquals(carModelDto.getModelName(), returnedCarModelDto.getModelName());
        assertEquals(carModelDto.getCarBrand().getId(), returnedCarModelDto.getCarBrand().getId());
        assertEquals(carModelDto.getCarBrand().getBrandName(), returnedCarModelDto.getCarBrand().getBrandName());
    }

    @Test
    public void countAllModelsShouldCallDao() {
        // Given
        // When
        targetObject.countAllModels();
        // Then
        verify(modelDao).count();
    }

    @Test
    public void countAllModelsByBrandShouldCallDao() {
        // Given
        // When
        targetObject.countAllModelsByBrand(1L);
        // Then
        verify(modelDao).countByCarBrand_Id(1L);
    }

    // TODO: more tests
}