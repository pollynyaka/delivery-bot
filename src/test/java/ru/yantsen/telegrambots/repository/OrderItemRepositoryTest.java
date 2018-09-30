package ru.yantsen.telegrambots.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.yantsen.telegrambots.configuration.TestDataConfiguration;
import ru.yantsen.telegrambots.entity.Order;
import ru.yantsen.telegrambots.entity.OrderItem;
import ru.yantsen.telegrambots.entity.Product;
import ru.yantsen.telegrambots.entity.Status;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@Transactional
public class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;


    public Order createOrder() {
        Order order = new Order();
        order.setOrderNumber("Order number TEST");
        order.setClientName("Client name TEST");
        order.setAddress("Client address TEST");
        order.setStatus(Status.ACCEPTED);
        orderRepository.save(order);
        return order;
    }


    public Product createProduct() {
        Product product = new Product();
        product.setModel("Product model TEST");
        productRepository.save(product);
        return product;
    }


    @Before
    public void createItem() {
        OrderItem item = new OrderItem();
        item.setOrder(createOrder());
        item.setProduct(createProduct());
        orderItemRepository.save(item);
    }


    @Test
    public void findAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        orderItems.forEach(item -> System.out.println(item.toString()));
        assertTrue(orderItems.size() == 1);
    }


}
