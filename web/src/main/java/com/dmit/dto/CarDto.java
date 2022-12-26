package com.dmit.dto;

import com.dmit.entity.car.BodyType;
import com.dmit.entity.car.FuelType;
import com.dmit.entity.car.TransmissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

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
    @Range(min = 1800, max = 2400)
    private int year;
    @Size(min = 2, max = 20)
    private String color;
    @Range(min = 0, max = 40)
    private float enginePower;
    //TODO: enum validation
    @NotNull
    private FuelType fuelType;
    @Range(min = 0, max = 400)
    private float fuelConsumption;
    //TODO: enum validation
    @NotNull
    private TransmissionType transmission;
    @Range(min = 0, max = 400)
    private int numberOfSeats;
    private boolean abs;
    private boolean cruiseControl;
    private boolean heatedSeats;
    private boolean climateControl;
    private boolean airBags;
    @Range(min = 0, max = 8000000)
    private BigDecimal price;
    //TODO: enum validation
    @NotNull
    private BodyType bodyType;
    @NotNull
    private CarModelDto carModel;
}
