package com.dmit.service;

import com.dmit.dao.OrderDao;
import com.dmit.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.validation.Validator;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    @Spy
    ModelMapper modelMapper = new ModelMapper();
    @Mock
    Validator validator;
    @Mock
    OrderDao orderDao;
    @InjectMocks
    OrderServiceImpl targetObject;

    public OrderServiceImplTest() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

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

    // TODO: more tests
}