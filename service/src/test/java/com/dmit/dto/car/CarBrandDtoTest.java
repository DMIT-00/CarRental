package com.dmit.dto.car;

import com.dmit.dto.BaseDtoTest;
import com.dmit.dto.mapper.CarBrandDtoMapper;
import com.dmit.dto.mapper.CarBrandDtoMapperImpl;
import com.dmit.entity.car.CarBrand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarBrandDtoTest extends BaseDtoTest {
    private CarBrandDto targetObject;
    private CarBrandDtoMapper carBrandDtoMapper = new CarBrandDtoMapperImpl();
    private final int TARGET_CLASS_NUMBER_OF_FIELDS = 2;

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = carBrandDtoMapper.toDto(carBrand);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), carBrand.getId());
        assertEquals(targetObject.getBrandName(), carBrand.getBrandName());
    }

    @Test
    public void mappingFromDto() {
        // Given
        targetObject = carBrandDtoMapper.toDto(carBrand); // TODO: don't use mapping, so we don't fail when toDto fails

        // When
        CarBrand carBrandResult = carBrandDtoMapper.fromDto(targetObject);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(carBrandResult.getId(), carBrand.getId());
        assertEquals(carBrandResult.getBrandName(), carBrand.getBrandName());
    }
}