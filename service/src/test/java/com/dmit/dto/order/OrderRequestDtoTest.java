package com.dmit.dto.order;

import com.dmit.dto.BaseDtoTest;
import com.dmit.entity.order.Order;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderRequestDtoTest extends BaseDtoTest {
    OrderRequestDto targetObject;
    final int TARGET_CLASS_NUMBER_OF_FIELDS = 3;

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = modelMapper.map(order, OrderRequestDto.class);

        // Then
//        try {
//            modelMapper.validate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
//        }

        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(targetObject.getCarId(), null);
        assertEquals(targetObject.getStartDate(), order.getStartDate());
        assertEquals(targetObject.getEndDate(), order.getEndDate());
    }

    @Test
    public void mappingFromDto() {
        // Given
        targetObject = modelMapper.map(order, OrderRequestDto.class);

        // When
        Order orderResult = modelMapper.map(targetObject, Order.class);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(orderResult.getStartDate(), order.getStartDate());
        assertEquals(orderResult.getEndDate(), order.getEndDate());
    }
}