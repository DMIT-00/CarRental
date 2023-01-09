package com.dmit.dto.car;

import com.dmit.dto.order.OrderDto;
import com.dmit.entity.car.BodyType;
import com.dmit.entity.car.FuelType;
import com.dmit.entity.car.TransmissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    UUID id;
    @NotNull
    @Range(min = 1800, max = 2400)
    private int year;
    @NotNull
    @Size(min = 2, max = 20)
    private String color;
    @NotNull
    @Range(min = 0, max = 40)
    private float enginePower;
    //TODO: enum validation
    @NotNull
    private FuelType fuelType;
    @NotNull
    @Range(min = 0, max = 400)
    private float fuelConsumption;
    //TODO: enum validation
    @NotNull
    private TransmissionType transmission;
    @NotNull
    @Range(min = 0, max = 400)
    private int numberOfSeats;
    @NotNull
    private boolean abs;
    @NotNull
    private boolean cruiseControl;
    @NotNull
    private boolean heatedSeats;
    @NotNull
    private boolean climateControl;
    @NotNull
    private boolean airBags;
    @NotNull
    @Range(min = 0, max = 8000000)
    private BigDecimal price;
    //TODO: enum validation
    @NotNull
    private BodyType bodyType;
    @NotNull
    private CarModelDto carModel;
    @Valid
    private OrderDto activeOrder;
}
