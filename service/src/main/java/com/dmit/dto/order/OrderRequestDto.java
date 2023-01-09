package com.dmit.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    @Future
    @NotNull
    Timestamp startDate;
    @NotNull
    Long numberOfHours;
    @NotNull
    private UUID carId;
}
