package com.dmit.dto.mapper;

import com.dmit.dto.order.OrderDto;
import com.dmit.entity.order.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {CarIdDtoMapper.class, UserIdDtoMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderDtoMapper {
    OrderDto toDto(Order order);
    Order fromDto(OrderDto orderDto);

    List<OrderDto> toDto(List<Order> order);
    List<Order> fromDto(List<OrderDto> orderDto);
}
