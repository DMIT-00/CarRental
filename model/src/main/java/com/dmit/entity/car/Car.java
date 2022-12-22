package com.dmit.entity.car;

import com.dmit.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_car")
public class Car {
    @Id
    @Column(name = "car_id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "engine_power", nullable = false)
    private float enginePower;

    @Column(name = "fuel_type", nullable = false)
    private FuelType fuelType;

    @Column(name = "fuel_consumption", nullable = false)
    private float fuelConsumption;

    @Column(name = "transmission", nullable = false)
    private TransmissionType transmission;

    @Column(name = "number_of_seats", nullable = false)
    private int numberOfSeats;

    @Column(name = "abs", nullable = false)
    private boolean abs;

    @Column(name = "cruise_control", nullable = false)
    private boolean cruiseControl;

    @Column(name = "heated_seats", nullable = false)
    private boolean heatedSeats;

    @Column(name = "climate_control", nullable = false)
    private boolean climateControl;

    @Column(name = "air_bags", nullable = false)
    private boolean airBags;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "body_type", nullable = false)
    private BodyType bodyType;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private CarBrand carBrand;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private CarModel carModel;

    // TODO: fetchtype fix
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Image> images;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Car{");
        sb.append("id=").append(id);
        sb.append(", year=").append(year);
        sb.append(", color='").append(color).append('\'');
        sb.append(", enginePower=").append(enginePower);
        sb.append(", fuelType=").append(fuelType);
        sb.append(", fuelConsumption=").append(fuelConsumption);
        sb.append(", transmission=").append(transmission);
        sb.append(", numberOfSeats=").append(numberOfSeats);
        sb.append(", abs=").append(abs);
        sb.append(", cruiseControl=").append(cruiseControl);
        sb.append(", heatedSeats=").append(heatedSeats);
        sb.append(", climateControl=").append(climateControl);
        sb.append(", airBags=").append(airBags);
        sb.append(", price=").append(price);
        sb.append(", bodyType=").append(bodyType);
        sb.append(", carBrand=").append(carBrand.getBrandName());
        sb.append(", carModel=").append(carModel.getModelName());
        //sb.append(", images=").append(images);
        sb.append('}');
        return sb.toString();
    }
}
