package com.dmit.dto.order;

import com.dmit.entity.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private UUID id;
    @NotNull
    private OrderStatus orderStatus;
    @Future
    @NotNull
    private Timestamp startDate;
    @NotNull
    private Long numberOfHours;
    @NotNull
    private BigDecimal totalPrice;
}
