package com.dmit.dto.car;

import com.dmit.dto.BaseDtoTest;
import com.dmit.entity.car.CarBrand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CarBrandDtoTest extends BaseDtoTest {
    CarBrandDto targetObject;
    final int TARGET_CLASS_NUMBER_OF_FIELDS = 2;

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = modelMapper.map(carBrand, CarBrandDto.class);

        // Then
        try {
            modelMapper.validate();
        } catch (Exception e) {
            e.printStackTrace();
            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
        }

        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), carBrand.getId());
        assertEquals(targetObject.getBrandName(), carBrand.getBrandName());
    }

    @Test
    public void mappingFromDto() {
        // Given
        targetObject = modelMapper.map(carBrand, CarBrandDto.class);

        // When
        CarBrand carBrandResult = modelMapper.map(targetObject, CarBrand.class);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(carBrandResult.getId(), carBrand.getId());
        assertEquals(carBrandResult.getBrandName(), carBrand.getBrandName());
    }
}