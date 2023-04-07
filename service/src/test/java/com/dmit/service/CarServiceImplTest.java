package com.dmit.service;

import com.dmit.dao.CarDao;
import com.dmit.dto.car.CarDto;
import com.dmit.dto.mapper.*;
import com.dmit.entity.car.Car;
import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {
    @Spy
    private CarDtoMapper carDtoMapper = new CarDtoMapperImpl(new CarModelDtoMapperImpl(new CarBrandDtoMapperImpl()),
            new OrderIdDtoMapperImpl());
    @Mock
    private ValidationService<CarDtoMapper> validationService;
    @Mock
    private CarDao carDao;
    @InjectMocks
    private CarServiceImpl targetObject;

    @Test
    public void addCarShouldThrowOnDuplicateId() {
        // Given
        CarDto carDto = new CarDto();
        carDto.setId(UUID.randomUUID());

        // When
        when(carDao.existsById(carDto.getId()))
                .thenReturn(true);

        // Then
        Exception exception = assertThrows(AlreadyExistsException.class, () -> targetObject.addCar(carDto));
        assertEquals(exception.getMessage(), "Car already exists! Id: " + carDto.getId());
    }

    @Test
    public void addCarShouldCallDao() {
        // Given
        CarDto carDto = new CarDto();
        carDto.setId(UUID.randomUUID());

        // When
        when(carDao.save(any())).thenReturn(new Car());
        targetObject.addCar(carDto);

        // Then
        ArgumentCaptor<Car> argument = ArgumentCaptor.forClass(Car.class);
        verify(carDao).save(argument.capture());
        assertEquals(carDto.getId(), argument.getValue().getId());
    }

    @Test
    public void updateCarShouldThrowWhenCarDoesNotExist() {
        // Given
        CarDto carDto = new CarDto();
        carDto.setId(UUID.randomUUID());

        // When
        when(carDao.existsById(carDto.getId()))
                .thenReturn(false);

        // Then
        Exception exception = assertThrows(NotFoundException.class, () -> targetObject.updateCar(carDto));
        assertEquals(exception.getMessage(), "Car not found! Id: " + carDto.getId());
    }

    @Test
    public void updateCarShouldCallDao() {
        // Given
        CarDto carDto = new CarDto();
        carDto.setId(UUID.randomUUID());

        // When
        when(carDao.existsById(carDto.getId()))
                .thenReturn(true);
        when(carDao.save(any())).thenReturn(new Car());
        targetObject.updateCar(carDto);

        // Then
        ArgumentCaptor<Car> argument = ArgumentCaptor.forClass(Car.class);
        verify(carDao).save(argument.capture());
        assertEquals(carDto.getId(), argument.getValue().getId());
    }

    // TODO: more tests
}