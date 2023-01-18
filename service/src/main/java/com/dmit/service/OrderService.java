package com.dmit.service;

import com.dmit.dao.CarDao;
import com.dmit.dao.OrderDao;
import com.dmit.dao.UserDao;
import com.dmit.dto.order.OrderDto;
import com.dmit.dto.order.OrderRequestDto;
import com.dmit.entity.car.Car;
import com.dmit.entity.order.Order;
import com.dmit.entity.order.OrderStatus;
import com.dmit.entity.user.User;
import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.InvalidOperation;
import com.dmit.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public OrderDto addOrder(OrderRequestDto orderRequestDto) {
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
                .orElseThrow(() -> new NotFoundException("Car not found! Id: " + orderRequestDto.getCarId()));

        // TODO: check if car is busy

        Order order = modelMapper.map(orderRequestDto, Order.class);

        // Check for duplicate Id
        if (order.getId() != null && orderDao.findById(order.getId()).isPresent())
            throw new AlreadyExistsException("Order already exists! Id: " + order.getId());

        order.setOrderStatus(OrderStatus.PAYMENT);

        order.addCar(car);
        order.addUser(user);

        order.setStartDate(order.getStartDate().truncatedTo(ChronoUnit.MINUTES));
        order.setEndDate(order.getEndDate().truncatedTo(ChronoUnit.MINUTES));

        long minutes = ChronoUnit.MINUTES.between(order.getStartDate(), order.getEndDate());

        if (minutes < 10)
            throw new InvalidOperation("Duration can't be less than 10!");

        order.setTotalPrice(car.getPrice().multiply(BigDecimal.valueOf(minutes)));

        return modelMapper.map(orderDao.save(order), OrderDto.class);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public OrderDto updateOrder(OrderDto updatedOrder) {
        Order order = orderDao.findById(updatedOrder.getId())
                .orElseThrow(() -> new NotFoundException("Order not found! Id: " + updatedOrder.getId()));

        order.setOrderStatus(updatedOrder.getOrderStatus());
        order.setStartDate(updatedOrder.getStartDate());
        order.setEndDate(updatedOrder.getEndDate());

        long minutes = ChronoUnit.MINUTES.between(order.getStartDate(), order.getEndDate());

        if (minutes < 10)
            throw new InvalidOperation("Duration can't be less than 10!");

        order.setTotalPrice(order.getCar().getPrice().multiply(BigDecimal.valueOf(minutes)));

        return modelMapper.map(orderDao.save(order), OrderDto.class);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public void deleteOrder(UUID id) {
        if (!orderDao.existsById(id))
            throw new NotFoundException("Order not found! Id: " + id);

        orderDao.deleteById(id);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public OrderDto findOrderById(UUID orderId) {
        Order order = orderDao.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found! Id: " + orderId));

        return modelMapper.map(order, OrderDto.class);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public long countAllOrdersByStatus(OrderStatus orderStatus) {
        return orderDao.countByOrderStatus(orderStatus);
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public List<OrderDto> findAllOrdersByStatusPageable(OrderStatus orderStatus, int page, int size) {
        return orderDao.findAllByOrderStatus(orderStatus, PageRequest.of(page, size)).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public long countAllOrders() {
        return orderDao.count();
    }

    @Transactional
    @Secured("ROLE_MANAGER")
    public List<OrderDto> findAllOrdersPageable(int page, int size) {
        return orderDao.findAll(PageRequest.of(page, size)).stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

}