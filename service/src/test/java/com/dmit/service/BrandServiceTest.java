package com.dmit.service;

import com.dmit.dao.CarBrandDao;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.entity.car.CarBrand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BrandServiceTest {

    @Spy
    ModelMapper modelMapper = new ModelMapper();
    @Mock
    Validator validator;
    @Mock
    CarBrandDao carBrandDao;
    @InjectMocks
    BrandService targetObject;

    public BrandServiceTest() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    public void getAllBrandsShouldReturnList() {
        // Given
        List<CarBrandDto> expectedList = List.of(
                new CarBrandDto(1L, "BMW"),
                new CarBrandDto(2L, "SUZUKI")
        );

        when(carBrandDao.findAll()).thenReturn(List.of(
                new CarBrand(1L, "BMW", new ArrayList<>()),
                new CarBrand(2L, "SUZUKI", new ArrayList<>())
        ));

        // When
        List<CarBrandDto> resultList = targetObject.getAllBrands();

        // Then
        assertEquals(resultList.size(), 2);
        assertEquals(resultList.get(0).getId(), expectedList.get(0).getId());
        assertEquals(resultList.get(0).getBrandName(), expectedList.get(0).getBrandName());

        assertEquals(resultList.get(1).getId(), expectedList.get(1).getId());
        assertEquals(resultList.get(1).getBrandName(), expectedList.get(1).getBrandName());
    }

    @Test
    public void addNewBrandShouldCallDao() {
        // Given
        CarBrandDto carBrandDto = new CarBrandDto(1L, "BMW");

        // When
        targetObject.addNewBrand(carBrandDto);

        // Then
        ArgumentCaptor<CarBrand> argument = ArgumentCaptor.forClass(CarBrand.class);
        verify(carBrandDao).save(argument.capture());
        assertNull(argument.getValue().getId());
        assertEquals(argument.getValue().getBrandName(), carBrandDto.getBrandName());
    }

}