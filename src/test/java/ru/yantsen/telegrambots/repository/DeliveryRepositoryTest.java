package ru.yantsen.telegrambots.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.yantsen.telegrambots.configuration.TestDataConfiguration;
import ru.yantsen.telegrambots.entity.Delivery;
import ru.yantsen.telegrambots.entity.Driver;
import ru.yantsen.telegrambots.entity.Order;
import ru.yantsen.telegrambots.entity.Status;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@Transactional
public class DeliveryRepositoryTest {


    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private OrderRepository orderRepository;


    Driver driverTest;

    public Driver createDriver() {
        Driver driver = new Driver();
        driver.setName("Driver test");
        driver.setPhoneNumber("0123343455");
        driver.setUserId(new Long(9999999));
        driverRepository.save(driver);
        driverTest = driver;
        System.out.println("Driver info:" + driverTest);
        return driver;
    }


    public Order createOrder() {
        Order order = new Order();
        order.setOrderNumber("Order number TEST");
        order.setClientName("Client name TEST");
        order.setAddress("Client address TEST");
        order.setStatus(Status.ACCEPTED);
        orderRepository.save(order);
        System.out.println("Order info:" + order);
        return order;
    }

    @Before
    public void createDelivery() {
        Delivery delivery = new Delivery();
        delivery.setDriver(createDriver());
        delivery.setOrder(createOrder());
        delivery.setDate(new Date());
        deliveryRepository.save(delivery);
    }

    @After
    public void finalize() {
        driverTest = null;
    }


    @Test
    public void findDriverById() {
        assertTrue(driverRepository.findById(driverTest.getId()).isPresent());
    }

    @Test
    public void findDriverByUserId() {
        assertNotNull(driverRepository.findByUserId(driverTest.getUserId()));
    }


    @Test
    public void findDeliveries() {
        List<Delivery> deliveries = deliveryRepository.
                findByDriverIdAndDeliveryDateAndOrder_Status(driverTest.getId(), new Date(), Status.ACCEPTED);
        deliveries.forEach(System.out::println);
        assertFalse(deliveries.isEmpty());
    }


}
