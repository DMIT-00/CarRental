package com.dmit.service;

import com.dmit.dao.CarDao;
import com.dmit.dto.car.CarDto;
import com.dmit.exception.AlreadyExistsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.validation.Validator;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceImplTest {
    @Spy
    ModelMapper modelMapper = new ModelMapper();
    @Mock
    Validator validator;
    @Mock
    CarDao carDao;
    @InjectMocks
    CarServiceImpl targetObject;
    
    public CarServiceImplTest() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Test
    public void addNewCarShouldThrowOnDuplicateId() {
        // Given
        CarDto carDto = new CarDto();
        carDto.setId(UUID.randomUUID());

        // When
        when(carDao.existsById(carDto.getId()))
                .thenReturn(true);

        // Then
        assertThrows(AlreadyExistsException.class, () -> targetObject.addCar(carDto));
    }

    // TODO: more tests
}