package com.dmit.dto.car;

import com.dmit.dto.BaseDtoTest;
import com.dmit.dto.mapper.CarBrandDtoMapperImpl;
import com.dmit.dto.mapper.CarModelDtoMapper;
import com.dmit.dto.mapper.CarModelDtoMapperImpl;
import com.dmit.entity.car.CarModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarModelDtoTest extends BaseDtoTest {
    CarModelDto targetObject;
    CarModelDtoMapper mapper = new CarModelDtoMapperImpl(new CarBrandDtoMapperImpl());
    final int TARGET_CLASS_NUMBER_OF_FIELDS = 3;

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = mapper.toDto(carModel);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), carModel.getId());
        assertEquals(targetObject.getCarBrand().getId(), carModel.getCarBrand().getId());
        assertEquals(targetObject.getCarBrand().getBrandName(), carModel.getCarBrand().getBrandName());
    }

    @Test
    public void mappingFromDto() {
        // Given
        targetObject = mapper.toDto(carModel); // TODO: don't use mapping, so we don't fail when toDto fails

        // When
        CarModel carModelResult = mapper.fromDto(targetObject);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(carModelResult.getId(), carModel.getId());
        assertEquals(carModelResult.getCarBrand().getId(), carModel.getCarBrand().getId());
        assertEquals(carModelResult.getCarBrand().getBrandName(), carModel.getCarBrand().getBrandName());
    }
}