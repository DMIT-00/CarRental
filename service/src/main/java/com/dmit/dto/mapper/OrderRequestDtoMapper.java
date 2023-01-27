package com.dmit.dto.mapper;

import com.dmit.dto.order.OrderRequestDto;
import com.dmit.entity.order.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderRequestDtoMapper {
    // TODO: carId
    OrderRequestDto toDto(Order order);
    Order fromDto(OrderRequestDto orderRequestDto);
}
