package com.dmit.dao;

import com.dmit.config.DataConfig;
import com.dmit.entity.order.OrderStatus;
import lombok.SneakyThrows;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@TestPropertySource(value = {
        "classpath:/database-test.properties",
        "classpath:/hibernate-test.properties"
})
public class OrderDaoTest {
    @Autowired
    DataSource dataSource;
    @Autowired
    OrderDao orderDao;

    IDatabaseConnection iDatabaseConnection;

    @Before
    @SneakyThrows
    public void setUp() {
        iDatabaseConnection = new MySqlConnection(dataSource.getConnection(), "CarRentalTest");
    }

    @Test
    @SneakyThrows
    public void countByOrderStatusTest() {
        // Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(OrderDaoTest.class.getResourceAsStream("/dbunit/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        // When
        long paymentCount = orderDao.countByOrderStatus(OrderStatus.PAYMENT);
        long paidCount = orderDao.countByOrderStatus(OrderStatus.PAID);
        long carInUseCount = orderDao.countByOrderStatus(OrderStatus.CAR_IN_USE);
        long carReturnedCount = orderDao.countByOrderStatus(OrderStatus.CAR_RETURNED);
        long closedCount = orderDao.countByOrderStatus(OrderStatus.CLOSED);

        // Then
        assertEquals(0, paymentCount);
        assertEquals(1, paidCount);
        assertEquals(2, carInUseCount);
        assertEquals(3, carReturnedCount);
        assertEquals(4, closedCount);

        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }

    @Test
    @SneakyThrows
    public void countActiveOrdersByCarInDateInterval() {
        // Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(OrderDaoTest.class.getResourceAsStream("/dbunit/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        // When
        long countedOrders = orderDao.countActiveOrdersByCarInDateInterval(
                UUID.fromString("00000000-0000-1002-0000-000000000000"),
                LocalDateTime.parse("2023-01-23T12:30:00"),
                LocalDateTime.parse("2023-01-23T13:30:00")
        );

        // Then
        assertEquals(1, countedOrders);
        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }

    @Test
    @SneakyThrows
    public void countActiveOrdersByCarInDateIntervalShouldSkipClosedOrders() {
        // Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(OrderDaoTest.class.getResourceAsStream("/dbunit/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        // When
        long countedOrders = orderDao.countActiveOrdersByCarInDateInterval(
                UUID.fromString("00000000-0000-1002-0000-000000000000"),
                LocalDateTime.parse("2023-01-24T12:00:00.000000"),
                LocalDateTime.parse("2023-01-24T13:00:00.000000")
        );

        // Then
        assertEquals(0, countedOrders);
        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }

    @Test
    @SneakyThrows
    public void countActiveOrdersByUserInDateInterval() {
        // Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(OrderDaoTest.class.getResourceAsStream("/dbunit/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        // When
        long countedOrders = orderDao.countActiveOrdersByUserInDateInterval(
                UUID.fromString("00000000-1002-0000-0000-000000000000"),
                LocalDateTime.parse("2023-01-22T12:00:00.000000"),
                LocalDateTime.parse("2023-01-22T13:00:00.000000")
        );

        // Then
        assertEquals(1, countedOrders);
        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }

    @Test
    @SneakyThrows
    public void countActiveOrdersByUserInDateIntervalShouldSkipClosedOrders() {
        // Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(OrderDaoTest.class.getResourceAsStream("/dbunit/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        // When
        long countedOrders = orderDao.countActiveOrdersByUserInDateInterval(
                UUID.fromString("00000000-1002-0000-0000-000000000000"),
                LocalDateTime.parse("2023-01-25T12:00:00.000000"),
                LocalDateTime.parse("2023-01-25T13:00:00.000000")
        );

        // Then
        assertEquals(0, countedOrders);
        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }


    @Test
    @SneakyThrows
    public void countActiveOrdersByCarInDateIntervalExceptOrderWithId() {
        // Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(OrderDaoTest.class.getResourceAsStream("/dbunit/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        // When
        long countedOrders = orderDao.countActiveOrdersByCarInDateIntervalExceptOrderWithId(
                UUID.fromString("00000000-0000-0000-1005-000000000000"),
                UUID.fromString("00000000-0000-1002-0000-000000000000"),
                LocalDateTime.parse("2023-01-23T12:30:00"),
                LocalDateTime.parse("2023-01-23T13:30:00")
        );

        // Then
        assertEquals(0, countedOrders);
        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }

    @Test
    @SneakyThrows
    public void countActiveOrdersByUserInDateIntervalExceptOrderWithId() {
        // Given
        IDataSet meetingDataSet = new FlatXmlDataSetBuilder()
                .build(OrderDaoTest.class.getResourceAsStream("/dbunit/orders-data.xml"));
        DatabaseOperation.CLEAN_INSERT.execute(iDatabaseConnection, meetingDataSet);

        // When
        long countedOrders = orderDao.countActiveOrdersByUserInDateIntervalExceptOrderWithId(
                UUID.fromString("00000000-0000-0000-1003-000000000000"),
                UUID.fromString("00000000-1002-0000-0000-000000000000"),
                LocalDateTime.parse("2023-01-22T12:00:00.000000"),
                LocalDateTime.parse("2023-01-22T13:00:00.000000")
        );

        // Then
        assertEquals(0, countedOrders);
        DatabaseOperation.DELETE_ALL.execute(iDatabaseConnection, meetingDataSet);
    }
}