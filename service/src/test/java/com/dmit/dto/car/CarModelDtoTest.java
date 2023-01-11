package com.dmit.dto.car;

import com.dmit.dto.BaseDtoTest;
import com.dmit.entity.car.CarBrand;
import com.dmit.entity.car.CarModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarModelDtoTest extends BaseDtoTest {
    CarModelDto targetObject;
    final int TARGET_CLASS_NUMBER_OF_FIELDS = 3;

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = modelMapper.map(carModel, CarModelDto.class);

        // Then
        try {
            modelMapper.validate();
        } catch (Exception e) {
            e.printStackTrace();
            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
        }

        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), carModel.getId());
        assertEquals(targetObject.getCarBrand().getId(), carModel.getCarBrand().getId());
        assertEquals(targetObject.getCarBrand().getBrandName(), carModel.getCarBrand().getBrandName());
    }

    @Test
    public void mappingFromDto() {
        // Given
        targetObject = modelMapper.map(carModel, CarModelDto.class);

        // When
        CarModel carModelResult = modelMapper.map(targetObject, CarModel.class);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(carModelResult.getId(), carModel.getId());
        assertEquals(carModelResult.getCarBrand().getId(), carModel.getCarBrand().getId());
        assertEquals(carModelResult.getCarBrand().getBrandName(), carModel.getCarBrand().getBrandName());
    }
}