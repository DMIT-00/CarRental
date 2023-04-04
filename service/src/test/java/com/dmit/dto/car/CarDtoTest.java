package com.dmit.dto.car;

import com.dmit.dto.BaseDtoTest;
import com.dmit.dto.mapper.*;
import com.dmit.entity.car.Car;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarDtoTest extends BaseDtoTest {
    private CarDto targetObject;
    private CarDtoMapper carDtoMapper = new CarDtoMapperImpl(new CarModelDtoMapperImpl(new CarBrandDtoMapperImpl()), new OrderIdDtoMapperImpl());
    private final int TARGET_CLASS_NUMBER_OF_FIELDS = 17;

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = carDtoMapper.toDto(car);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), car.getId());
        assertEquals(targetObject.getColor(), car.getColor());
        assertEquals(targetObject.getBodyType(), car.getBodyType());
        assertEquals(targetObject.getEnginePower(), car.getEnginePower(), 0.000000001f);
        assertEquals(targetObject.getFuelType(), car.getFuelType());
        assertEquals(targetObject.getYear(), car.getYear());
        assertEquals(targetObject.getPrice(), car.getPrice());

        assertEquals(targetObject.isAbs(), car.isAbs());
        assertEquals(targetObject.isAirBags(), car.isAirBags());
        assertEquals(targetObject.isClimateControl(), car.isClimateControl());
        assertEquals(targetObject.isCruiseControl(), car.isCruiseControl());
        assertEquals(targetObject.isHeatedSeats(), car.isHeatedSeats());

        // TODO: more assertions
    }

    @Test
    public void mappingFromDto() {
        // Given
        targetObject = carDtoMapper.toDto(car); // TODO: don't use mapping, so we don't fail when toDto fails

        // When
        Car carResult = carDtoMapper.fromDto(targetObject);

        // Then
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(carResult.getId(), car.getId());
        assertEquals(carResult.getColor(), car.getColor());
        assertEquals(carResult.getBodyType(), car.getBodyType());
        assertEquals(carResult.getEnginePower(), car.getEnginePower(), 0.000000001f);
        assertEquals(carResult.getFuelType(), car.getFuelType());
        assertEquals(carResult.getYear(), car.getYear());
        assertEquals(carResult.getPrice(), car.getPrice());

        assertEquals(carResult.isAbs(), car.isAbs());
        assertEquals(carResult.isAirBags(), car.isAirBags());
        assertEquals(carResult.isClimateControl(), car.isClimateControl());
        assertEquals(carResult.isCruiseControl(), car.isCruiseControl());
        assertEquals(carResult.isHeatedSeats(), car.isHeatedSeats());
    }
}