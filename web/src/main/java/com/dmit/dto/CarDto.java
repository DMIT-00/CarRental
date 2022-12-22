package com.dmit.dto;

import com.dmit.entity.car.BodyType;
import com.dmit.entity.car.FuelType;
import com.dmit.entity.car.TransmissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    UUID id;
    private int year;
    private String color;
    private float enginePower;
    private FuelType fuelType;
    private float fuelConsumption;
    private TransmissionType transmission;
    private int numberOfSeats;
    private boolean abs;
    private boolean cruiseControl;
    private boolean heatedSeats;
    private boolean climateControl;
    private boolean airBags;
    private BigDecimal price;
    private BodyType bodyType;
    private Long brandId;
    private String brandName;
    private Long modelId;
    private String modelName;
}
