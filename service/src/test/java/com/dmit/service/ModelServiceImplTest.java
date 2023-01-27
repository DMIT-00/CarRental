package com.dmit.service;

import com.dmit.dao.CarModelDao;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.dto.car.CarModelDto;
import com.dmit.dto.mapper.CarBrandDtoMapper;
import com.dmit.dto.mapper.CarBrandDtoMapperImpl;
import com.dmit.dto.mapper.CarModelDtoMapper;
import com.dmit.dto.mapper.CarModelDtoMapperImpl;
import com.dmit.entity.car.CarModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.Validator;

import static org.junit.Assert.assertEquals;
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
    public void addNewModelShouldCallDao() {
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

    // TODO: more tests
}