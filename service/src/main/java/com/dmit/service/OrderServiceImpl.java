package com.dmit.service;

import com.dmit.dao.CarDao;
import com.dmit.dao.OrderDao;
import com.dmit.dao.UserDao;
import com.dmit.dto.mapper.OrderDtoMapper;
import com.dmit.dto.mapper.OrderRequestDtoMapper;
import com.dmit.dto.order.OrderDto;
import com.dmit.dto.order.OrderRequestDto;
import com.dmit.entity.car.Car;
import com.dmit.entity.order.Order;
import com.dmit.entity.order.OrderStatus;
import com.dmit.entity.user.User;
import com.dmit.exception.AlreadyExistsException;
import com.dmit.exception.InvalidOperation;
import com.dmit.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {
    private final ValidationService<OrderRequestDto> validationService;
    private final OrderDtoMapper orderDtoMapper;
    private final OrderRequestDtoMapper orderRequestDtoMapper;
    private final UserService userService;
    private final OrderDao orderDao;
    private final UserDao userDao;
    private final CarDao carDao;

    @Override
    @Transactional
    @Secured("ROLE_USER")
    public OrderDto addOrder(OrderRequestDto orderRequestDto) {
        validationService.validate(orderRequestDto);

        // Get current user for the order
        String username = userService.findCurrentUser().getUsername();
        User user = userDao.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found! Username: " + username);

        // Get car for the order
        Car car = carDao.findById(orderRequestDto.getCar().getId())
                .orElseThrow(() -> new NotFoundException("Car not found! Id: " + orderRequestDto.getCar().getId()));

        Order order = orderRequestDtoMapper.fromDto(orderRequestDto);

        // Check for duplicate Id
        if (order.getId() != null && orderDao.findById(order.getId()).isPresent())
            throw new AlreadyExistsException("Order already exists! Id: " + order.getId());

        order.setOrderStatus(OrderStatus.PAYMENT);

        order.addCar(car);
        order.addUser(user);

        order.setStartDate(order.getStartDate().truncatedTo(ChronoUnit.MINUTES));
        order.setEndDate(order.getEndDate().truncatedTo(ChronoUnit.MINUTES));

        if (isUserBusyForOrder(order.getUser().getId(), order.getStartDate(), order.getEndDate()))
            throw new InvalidOperation("The user is busy in this time interval!");

        if (isCarBusyForOrder(order.getCar().getId(), order.getStartDate(), order.getEndDate()))
            throw new InvalidOperation("The car is busy in this time interval!");

        long minutes = ChronoUnit.MINUTES.between(order.getStartDate(), order.getEndDate());

        if (minutes < 10)
            throw new InvalidOperation("The duration of order can't be less than 10!");

        order.setTotalPrice(car.getPrice().multiply(BigDecimal.valueOf(minutes)));

        return orderDtoMapper.toDto(orderDao.save(order));
    }

    @Override
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

        if (isUserBusyForOrderExceptOrder(order.getId(), order.getUser().getId(), order.getStartDate(), order.getEndDate()))
            throw new InvalidOperation("The user is busy in this time interval!");

        if (isCarBusyForOrderExceptOrder(order.getId(), order.getCar().getId(), order.getStartDate(), order.getEndDate()))
            throw new InvalidOperation("The car is busy in this time interval!");

        order.setTotalPrice(order.getCar().getPrice().multiply(BigDecimal.valueOf(minutes)));

        return orderDtoMapper.toDto(orderDao.save(order));
    }

    @Override
    @Transactional
    public boolean isUserBusyForOrder(UUID userId, LocalDateTime startDate, LocalDateTime endDate) {
        return orderDao.countActiveOrdersByUserInDateInterval(userId, startDate, endDate) != 0;
    }

    @Override
    @Transactional
    public boolean isCarBusyForOrder(UUID carId, LocalDateTime startDate, LocalDateTime endDate) {
        return orderDao.countActiveOrdersByCarInDateInterval(carId, startDate, endDate) != 0;
    }

    @Override
    @Transactional
    public boolean isUserBusyForOrderExceptOrder(UUID orderId, UUID userId, LocalDateTime startDate, LocalDateTime endDate) {
        return orderDao.countActiveOrdersByUserInDateIntervalExceptOrderWithId(orderId, userId, startDate, endDate) != 0;
    }

    @Override
    @Transactional
    public boolean isCarBusyForOrderExceptOrder(UUID orderId, UUID carId, LocalDateTime startDate, LocalDateTime endDate) {
        return orderDao.countActiveOrdersByCarInDateIntervalExceptOrderWithId(orderId, carId, startDate, endDate) != 0;
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public void deleteOrder(UUID id) {
        if (!orderDao.existsById(id))
            throw new NotFoundException("Order not found! Id: " + id);

        orderDao.deleteById(id);
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public OrderDto findOrderById(UUID orderId) {
        Order order = orderDao.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found! Id: " + orderId));

        return orderDtoMapper.toDto(order);
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public long countAllOrdersByStatus(OrderStatus orderStatus) {
        return orderDao.countByOrderStatus(orderStatus);
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public List<OrderDto> findAllOrdersByStatusPageable(OrderStatus orderStatus, int page, int size) {
        return orderDao.findAllByOrderStatus(orderStatus, PageRequest.of(page, size)).stream()
                .map(orderDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public long countAllOrders() {
        return orderDao.count();
    }

    @Override
    @Transactional
    @Secured("ROLE_MANAGER")
    public List<OrderDto> findAllOrdersPageable(int page, int size) {
        return orderDao.findAll(PageRequest.of(page, size)).stream()
                .map(orderDtoMapper::toDto)
                .collect(Collectors.toList());
    }

}