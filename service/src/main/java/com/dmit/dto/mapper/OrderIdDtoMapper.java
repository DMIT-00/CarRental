package com.dmit.dto.mapper;

import com.dmit.dto.order.OrderIdDto;
import com.dmit.entity.order.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderIdDtoMapper {
    OrderIdDto toDto(Order order);
    Order fromDto(OrderIdDto orderIdDto);
}
