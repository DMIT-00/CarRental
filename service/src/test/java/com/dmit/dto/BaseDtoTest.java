package com.dmit.dto;

import com.dmit.entity.car.*;
import com.dmit.entity.order.Order;
import com.dmit.entity.order.OrderStatus;
import com.dmit.entity.user.Role;
import com.dmit.entity.user.User;
import com.dmit.entity.user.UserDetail;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class BaseDtoTest {
    protected User user;
    protected UserDetail userDetail;
    protected Role roleUser;
    protected Role roleAdmin;
    protected Car car;
    protected CarBrand carBrand;
    protected CarModel carModel;
    protected Order order;

    public BaseDtoTest() {
        roleUser = new Role(1000L, "USER", new HashSet<>());
        roleAdmin = new Role(2000L, "ADMIN", new HashSet<>());

        userDetail = new UserDetail(
                UUID.randomUUID(),
                "First Name",
                "Last Name",
                "+120000000",
                "0001000200030004",
                Date.valueOf("1980-02-04"),
                null
        );

        user = new User(
                userDetail.getId(),
                "abx@gmail.com",
                "username",
                "password",
                false,
                null,
                new HashSet<>(),
                new ArrayList<>()
        );

        user.addUserDetail(userDetail);
        user.addRole(roleUser);
        user.addRole(roleAdmin);

        carBrand = new CarBrand(4L, "BMW", new ArrayList<>());
        carModel = new CarModel(2L, "X5", null);
        carBrand.addModel(carModel);


        car = new Car(
                UUID.randomUUID(),
                2000,
                "RED",
                10.8f,
                FuelType.DIESEL,
                280.2f,
                TransmissionType.MANUAL,
                8,
                true,
                false,
                true,
                false,
                true,
                BigDecimal.valueOf(108.25),
                BodyType.JEEP,
                carModel,
                null,
                new ArrayList<>()
        );


        order = new Order(
                UUID.randomUUID(),
                OrderStatus.CLOSED,
                LocalDateTime.of(2024, 2, 3, 10, 0),
                LocalDateTime.of(2024, 2, 3, 12, 0),
                BigDecimal.valueOf(20.5),
                user,
                car
        );

        order.addUser(user);
        order.addCar(car);
    }
}
