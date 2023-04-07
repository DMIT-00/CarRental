package com.dmit.service;

import com.dmit.dao.CarDao;
import com.dmit.dao.OrderDao;
import com.dmit.dao.UserDao;
import com.dmit.dto.car.CarIdDto;
import com.dmit.dto.mapper.*;
import com.dmit.dto.order.OrderRequestDto;
import com.dmit.dto.user.UserResponseDto;
import com.dmit.entity.car.Car;
import com.dmit.entity.order.Order;
import com.dmit.entity.user.User;
import com.dmit.exception.InvalidOperation;
import com.dmit.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    @Spy
    private OrderDtoMapper orderDtoMapper = new OrderDtoMapperImpl(new CarIdDtoMapperImpl(), new UserIdDtoMapperImpl());
    @Spy
    private OrderRequestDtoMapper orderRequestDtoMapper = new OrderRequestDtoMapperImpl();

    @Mock
    private ValidationService<OrderRequestDto> validationService;
    @Mock
    private UserService userService;
    @Mock
    private OrderDao orderDao;
    @Mock
    private UserDao userDao;
    @Mock
    private CarDao carDao;
    @InjectMocks
    private OrderServiceImpl targetObject;

    @Test
    public void deleteOrderShouldThrow() {
        // Given
        UUID id = UUID.randomUUID();

        // When
        when(orderDao.existsById(id))
                .thenReturn(false);

        // Then
        assertThrows(NotFoundException.class, () -> targetObject.deleteOrder(id));
    }

    @Test
    public void deleteOrderShouldCallDao() {
        // Given
        UUID id = UUID.randomUUID();

        // When
        when(orderDao.existsById(id))
                .thenReturn(true);
        targetObject.deleteOrder(id);

        // Then
        verify(orderDao).deleteById(id);
    }

    @Test
    public void countAllOrdersShouldCallDao() {
        // Given
        // When
        targetObject.countAllOrders();

        // Then
        verify(orderDao).count();
    }

    @Test
    public void addOrderShouldThrowOnInvalidUser() {
        // Given
        OrderRequestDto orderDto = new OrderRequestDto();
        orderDto.setCar(new CarIdDto(UUID.randomUUID()));

        // When
        UserResponseDto user = new UserResponseDto();
        user.setUsername("User");
        when(userService.findCurrentUser()).thenReturn(user);
        when(userDao.findByUsername(any())).thenReturn(null);

        // Then
        assertThrows(UsernameNotFoundException.class, () -> targetObject.addOrder(orderDto));
    }

    @Test
    public void addOrderShouldThrowOnInvalidCardId() {
        // Given
        OrderRequestDto orderDto = new OrderRequestDto();
        orderDto.setCar(new CarIdDto(UUID.randomUUID()));

        // When
        UserResponseDto user = new UserResponseDto();
        user.setUsername("User");
        when(userService.findCurrentUser()).thenReturn(user);
        when(userDao.findByUsername(any())).thenReturn(new User());

        // Then
        Exception exception = assertThrows(NotFoundException.class, () -> targetObject.addOrder(orderDto));
        assertEquals(exception.getMessage(), "Car not found! Id: " + orderDto.getCar().getId());
    }

    @Test
    public void addOrderShouldThrowOnUserBusy() {
        // Given
        OrderRequestDto orderDto = new OrderRequestDto();
        orderDto.setStartDate(LocalDateTime.now());
        orderDto.setEndDate(LocalDateTime.now());

        orderDto.setCar(new CarIdDto(UUID.randomUUID()));

        // When
        UserResponseDto user = new UserResponseDto();
        user.setUsername("User");
        when(userService.findCurrentUser()).thenReturn(user);
        when(userDao.findByUsername(any())).thenReturn(new User());
        when(carDao.findById(any())).thenReturn(Optional.of(new Car()));
        when(orderDao.countActiveOrdersByUserInDateInterval(any(), any(), any())).thenReturn(1L);

        // Then
        Exception exception = assertThrows(InvalidOperation.class, () -> targetObject.addOrder(orderDto));
        assertEquals(exception.getMessage(), "The user is busy in this time interval!");
    }

    @Test
    public void addOrderShouldThrowOnCarBusy() {
        // Given
        OrderRequestDto orderDto = new OrderRequestDto();
        orderDto.setStartDate(LocalDateTime.now());
        orderDto.setEndDate(LocalDateTime.now());

        orderDto.setCar(new CarIdDto(UUID.randomUUID()));

        // When
        UserResponseDto user = new UserResponseDto();
        user.setUsername("User");
        when(userService.findCurrentUser()).thenReturn(user);
        when(userDao.findByUsername(any())).thenReturn(new User());
        when(carDao.findById(any())).thenReturn(Optional.of(new Car()));
        when(orderDao.countActiveOrdersByCarInDateInterval(any(), any(), any())).thenReturn(1L);

        // Then
        Exception exception = assertThrows(InvalidOperation.class, () -> targetObject.addOrder(orderDto));
        assertEquals(exception.getMessage(), "The car is busy in this time interval!");
    }

    @Test
    public void addOrderShouldThrowOnIntervalLessThan10() {
        // Given
        OrderRequestDto orderDto = new OrderRequestDto();
        orderDto.setStartDate(LocalDateTime.now());
        orderDto.setEndDate(LocalDateTime.now().plusMinutes(9));

        orderDto.setCar(new CarIdDto(UUID.randomUUID()));

        // When
        UserResponseDto user = new UserResponseDto();
        user.setUsername("User");
        when(userService.findCurrentUser()).thenReturn(user);
        when(userDao.findByUsername(any())).thenReturn(new User());
        when(carDao.findById(any())).thenReturn(Optional.of(new Car()));

        // Then
        Exception exception = assertThrows(InvalidOperation.class, () -> targetObject.addOrder(orderDto));
        assertEquals(exception.getMessage(), "The duration of order can't be less than 10!");
    }

    @Test
    public void addOrderShouldCallDao() {
        // Given
        OrderRequestDto orderDto = new OrderRequestDto();
        orderDto.setStartDate(LocalDateTime.now());
        orderDto.setEndDate(LocalDateTime.now().plusMinutes(11));

        orderDto.setCar(new CarIdDto(UUID.randomUUID()));

        // When
        UserResponseDto user = new UserResponseDto();
        user.setUsername("User");
        when(userService.findCurrentUser()).thenReturn(user);
        when(userDao.findByUsername(any())).thenReturn(new User());
        Car car = new Car();
        car.setId(orderDto.getCar().getId());
        car.setPrice(BigDecimal.valueOf(10));
        when(carDao.findById(any())).thenReturn(Optional.of(car));
        when(orderDao.save(any())).thenReturn(new Order());

        targetObject.addOrder(orderDto);

        // Then
        ArgumentCaptor<Order> argument = ArgumentCaptor.forClass(Order.class);
        verify(orderDao).save(argument.capture());
        assertEquals(orderDto.getCar().getId(), argument.getValue().getCar().getId());
    }

    // TODO: more tests
}