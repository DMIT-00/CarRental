package com.dmit.entity.car;

import com.dmit.entity.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_car")
public class Car {
    @Id
    @Column(name = "car_id", columnDefinition = "CHAR(36)")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Type(type = "uuid-char")
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
    @JoinColumn(name = "model_id", nullable = false)
    private CarModel carModel;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="car_id", nullable=false)
    private List<Image> images;

    @OneToMany(mappedBy = "car")
    private List<Order> orders = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return id != null && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
