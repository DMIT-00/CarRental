package com.dmit.service;

import com.dmit.dao.CarBrandDao;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.entity.car.CarBrand;
import com.dmit.exception.AlreadyExistsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BrandServiceImplTest {
    @Spy
    ModelMapper modelMapper = new ModelMapper();
    @Mock
    Validator validator;
    @Mock
    CarBrandDao brandDao;
    @InjectMocks
    BrandServiceImpl targetObject;

    public BrandServiceImplTest() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    public void findAllBrandsPageableShouldReturnList() {
        // Given
        List<CarBrandDto> expectedList = List.of(
                new CarBrandDto(1L, "BMW"),
                new CarBrandDto(2L, "SUZUKI")
        );

        when(brandDao.findAll(PageRequest.of(0, 20))).thenReturn(new PageImpl<>(List.of(
                new CarBrand(1L, "BMW", new ArrayList<>()),
                new CarBrand(2L, "SUZUKI", new ArrayList<>()))
        ));

        // When
        List<CarBrandDto> resultList = targetObject.findAllBrandsPageable(0, 20);

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
        targetObject.addBrand(carBrandDto);

        // Then
        ArgumentCaptor<CarBrand> argument = ArgumentCaptor.forClass(CarBrand.class);
        verify(brandDao).save(argument.capture());
        assertEquals(argument.getValue().getBrandName(), carBrandDto.getBrandName());
        assertEquals(argument.getValue().getId(), carBrandDto.getId());
    }

    @Test
    public void addNewBrandShouldThrowOnDuplicateId() {
        // Given
        CarBrandDto carBrandDto = new CarBrandDto(1L, "BMW");

        // When
        when(brandDao.findById(carBrandDto.getId()))
                .thenReturn(Optional.of(new CarBrand(1L, "Ferrari", null)));

        // Then
        assertThrows(AlreadyExistsException.class, () -> targetObject.addBrand(carBrandDto));
    }

    // TODO: more tests
}