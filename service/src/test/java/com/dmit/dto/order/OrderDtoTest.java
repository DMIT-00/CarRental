package com.dmit.dto.order;

import com.dmit.dto.BaseDtoTest;
import com.dmit.dto.mapper.CarIdDtoMapperImpl;
import com.dmit.dto.mapper.OrderDtoMapper;
import com.dmit.dto.mapper.OrderDtoMapperImpl;
import com.dmit.dto.mapper.UserIdDtoMapperImpl;
import com.dmit.entity.order.Order;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderDtoTest extends BaseDtoTest {
    private final int TARGET_CLASS_NUMBER_OF_FIELDS = 7;
    private OrderDto targetObject;
    private OrderDtoMapper mapper = new OrderDtoMapperImpl(new CarIdDtoMapperImpl(), new UserIdDtoMapperImpl());

    @Test
    public void mappingToDto() {
        // Given
        // see parent class

        // When
        targetObject = mapper.toDto(order);

        // Then
        // Will fail when the test class is changed, please add more assertions for new fields and change the number
        assertEquals(targetObject.getClass().getDeclaredFields().length, TARGET_CLASS_NUMBER_OF_FIELDS);

        assertEquals(targetObject.getId(), order.getId());
        assertEquals(targetObject.getOrderStatus(), order.getOrderStatus());
        assertEquals(targetObject.getStartDate(), order.getStartDate());
        assertEquals(targetObject.getEndDate(), order.getEndDate());
        assertEquals(targetObject.getTotalPrice(), order.getTotalPrice());
        assertEquals(targetObject.getUser().getId(), order.getUser().getId());
        assertEquals(targetObject.getCar().getId(), order.getCar().getId());
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

        assertEquals(orderResult.getId(), order.getId());
        assertEquals(orderResult.getOrderStatus(), order.getOrderStatus());
        assertEquals(orderResult.getStartDate(), order.getStartDate());
        assertEquals(orderResult.getEndDate(), order.getEndDate());
        assertEquals(orderResult.getTotalPrice(), order.getTotalPrice());
        assertEquals(orderResult.getUser().getId(), order.getUser().getId());
        assertEquals(orderResult.getCar().getId(), order.getCar().getId());
    }
}