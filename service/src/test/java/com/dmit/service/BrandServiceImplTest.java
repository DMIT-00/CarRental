package com.dmit.service;

import com.dmit.dao.CarBrandDao;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.dto.mapper.CarBrandDtoMapper;
import com.dmit.dto.mapper.CarBrandDtoMapperImpl;
import com.dmit.entity.car.CarBrand;
import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BrandServiceImplTest {
    @Spy
    private CarBrandDtoMapper carBrandDtoMapper = new CarBrandDtoMapperImpl();
    @Mock
    private Validator validator;
    @Mock
    private CarBrandDao brandDao;
    @InjectMocks
    private BrandServiceImpl targetObject;

    @Test
    public void addNewBrandShouldCallDao() {
        // Given
        CarBrandDto carBrandDto = new CarBrandDto(1L, "BMW");

        // When
        when(brandDao.save(any())).thenReturn(new CarBrand());
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
        when(brandDao.existsById(carBrandDto.getId()))
                .thenReturn(true);

        // Then
        assertThrows(AlreadyExistsException.class, () -> targetObject.addBrand(carBrandDto));
    }

    @Test
    public void updateBrandShouldThrowWhenBrandDoesNotExist() {
        // Given
        CarBrandDto carBrandDto = new CarBrandDto();
        carBrandDto.setId(1L);

        // When
        // Mockito will return false for brandDao.existsById(carBrandDto.getId()) by default

        // Then
        Exception exception = assertThrows(NotFoundException.class, () -> targetObject.updateBrand(carBrandDto));
        assertEquals(exception.getMessage(), "Brand not found! Id: " + carBrandDto.getId());
    }

    @Test
    public void updateBrandShouldCallDao() {
        // Given
        CarBrandDto carBrandDto = new CarBrandDto(1L, "BMW");

        // When
        when(brandDao.findById(carBrandDto.getId()))
                .thenReturn(Optional.of(new CarBrand(
                        carBrandDto.getId(),
                        carBrandDto.getBrandName(),
                        new ArrayList<>()
                )));
        targetObject.updateBrand(carBrandDto);

        // Then
        ArgumentCaptor<CarBrand> argument = ArgumentCaptor.forClass(CarBrand.class);
        verify(brandDao).save(argument.capture());
        assertEquals(argument.getValue().getBrandName(), carBrandDto.getBrandName());
        assertEquals(argument.getValue().getId(), carBrandDto.getId());
    }

    @Test
    public void deleteBrandShouldThrowWhenBrandDoesNotExist() {
        // Given
        CarBrandDto carBrandDto = new CarBrandDto();
        carBrandDto.setId(1L);

        // When
        when(brandDao.existsById(carBrandDto.getId()))
                .thenReturn(false);

        // Then
        Exception exception = assertThrows(NotFoundException.class, () -> targetObject.deleteBrand(carBrandDto.getId()));
        assertEquals(exception.getMessage(), "Brand not found! Id: " + carBrandDto.getId());
    }

    @Test
    public void deleteBrandShouldCallDao() {
        // Given
        CarBrandDto carBrandDto = new CarBrandDto();
        carBrandDto.setId(1L);

        // When
        when(brandDao.existsById(carBrandDto.getId()))
                .thenReturn(true);
        targetObject.deleteBrand(carBrandDto.getId());

        // Then
        ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
        verify(brandDao).deleteById(argument.capture());
        assertEquals(argument.getValue(), carBrandDto.getId());
    }

    @Test
    public void findBrandByIdShouldThrowWhenBrandDoesNotExist() {
        // Given
        CarBrandDto carBrandDto = new CarBrandDto();
        carBrandDto.setId(1L);

        // When
        when(brandDao.findById(carBrandDto.getId()))
                .thenReturn(Optional.empty());

        // Then
        Exception exception = assertThrows(NotFoundException.class, () -> targetObject.findBrandById(carBrandDto.getId()));
        assertEquals(exception.getMessage(), "Brand not found! Id: " + carBrandDto.getId());
    }

    @Test
    public void findBrandByIdShouldReturnBrand() {
        // Given
        CarBrandDto carBrandDto = new CarBrandDto(1L, "BMW");

        // When
        when(brandDao.findById(carBrandDto.getId()))
                .thenReturn(Optional.of(new CarBrand(
                        carBrandDto.getId(),
                        carBrandDto.getBrandName(),
                        new ArrayList<>())
                ));
        CarBrandDto returnedCarBrandDto = targetObject.findBrandById(carBrandDto.getId());

        // Then
        assertEquals(carBrandDto.getId(), returnedCarBrandDto.getId());
        assertEquals(carBrandDto.getBrandName(), returnedCarBrandDto.getBrandName());
    }

    @Test
    public void countAllBrandsShouldCallDao() {
        // Given
        // When
        targetObject.countAllBrands();
        // Then
        verify(brandDao).count();
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
}