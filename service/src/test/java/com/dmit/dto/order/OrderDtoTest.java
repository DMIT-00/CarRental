package com.dmit.dto.order;

import com.dmit.dto.BaseDtoTest;
import com.dmit.dto.car.CarBrandDto;
import com.dmit.entity.car.CarBrand;
import com.dmit.entity.order.Order;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OrderDtoTest extends BaseDtoTest {
    OrderDto targetObject;
    final int TARGET_CLASS_NUMBER_OF_FIELDS = 7;

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = modelMapper.map(order, OrderDto.class);

        // Then
        try {
            modelMapper.validate();
        } catch (Exception e) {
            e.printStackTrace();
            fail("ModelMapper exception. Some fields were not assigned by ModelMapper!");
        }

        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), order.getId());
        assertEquals(targetObject.getOrderStatus(), order.getOrderStatus());
        assertEquals(targetObject.getNumberOfHours(), order.getNumberOfHours());
        assertEquals(targetObject.getStartDate(), order.getStartDate());
        assertEquals(targetObject.getTotalPrice(), order.getTotalPrice());
        assertEquals(targetObject.getUser().getId(), order.getUser().getId());
        assertEquals(targetObject.getCar().getId(), order.getCar().getId());
    }

    @Test
    public void mappingFromDto() {
        // Given
        targetObject = modelMapper.map(order, OrderDto.class);

        // When
        Order orderResult = modelMapper.map(targetObject, Order.class);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(orderResult.getId(), order.getId());
        assertEquals(orderResult.getOrderStatus(), order.getOrderStatus());
        assertEquals(orderResult.getNumberOfHours(), order.getNumberOfHours());
        assertEquals(orderResult.getStartDate(), order.getStartDate());
        assertEquals(orderResult.getTotalPrice(), order.getTotalPrice());
        assertEquals(orderResult.getUser().getId(), order.getUser().getId());
        assertEquals(orderResult.getCar().getId(), order.getCar().getId());
    }
}