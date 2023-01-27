package com.dmit.dto.order;

import com.dmit.dto.BaseDtoTest;
import com.dmit.dto.mapper.OrderRequestDtoMapper;
import com.dmit.dto.mapper.OrderRequestDtoMapperImpl;
import com.dmit.entity.order.Order;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderRequestDtoTest extends BaseDtoTest {
    OrderRequestDto targetObject;
    OrderRequestDtoMapper mapper = new OrderRequestDtoMapperImpl();
    final int TARGET_CLASS_NUMBER_OF_FIELDS = 3;

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = mapper.toDto(order);

        // Then
//        try {
//            modelMapper.validate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
//        }

        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(targetObject.getCar().getId(), null);
        assertEquals(targetObject.getStartDate(), order.getStartDate());
        assertEquals(targetObject.getEndDate(), order.getEndDate());
    }

    @Test
    public void mappingFromDto() {
        // Given
        targetObject = mapper.toDto(order); // TODO: don't use mapping, so we don't fail when toDto fails

        // When
        Order orderResult = mapper.fromDto(targetObject);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(orderResult.getStartDate(), order.getStartDate());
        assertEquals(orderResult.getEndDate(), order.getEndDate());
    }
}