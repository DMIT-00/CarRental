package com.dmit.service;

import com.dmit.dao.CarDao;
import com.dmit.dao.OrderDao;
import com.dmit.dao.UserDao;
import com.dmit.dto.order.OrderRequestDto;
import com.dmit.entity.car.Car;
import com.dmit.entity.order.Order;
import com.dmit.entity.order.OrderStatus;
import com.dmit.entity.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    Validator validator;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    OrderDao orderDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CarDao carDao;

    @Transactional
    @Secured("ROLE_USER")
    public void createNewOrder(OrderRequestDto orderRequestDto) {
        Set<ConstraintViolation<OrderRequestDto>> violations = validator.validate(orderRequestDto);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<OrderRequestDto> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb, violations);
        }

        // Get current user for the order
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.findByUsername(auth.getName());
        if (user == null)
            throw new UsernameNotFoundException("User not found! Username: " + auth.getName());

        // Get car for the order
        Car car = carDao.findById(orderRequestDto.getCarId())
                .orElseThrow(() -> new UsernameNotFoundException("Car can't be found! Car ID " + orderRequestDto.getCarId())); // TODO: Custom exception

        Order order = modelMapper.map(orderRequestDto, Order.class);

        order.setId(null);
        order.setOrderStatus(OrderStatus.PAYMENT);

        order.setCar(car);
        order.setUser(user);

        order.setTotalPrice(car.getPrice().multiply(BigDecimal.valueOf(order.getNumberOfHours())));

        orderDao.save(order);

        user.setActiveOrder(order);
        userDao.save(user);

        car.setActiveOrder(order);
        carDao.save(car);
    }
}
