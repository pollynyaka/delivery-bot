package ru.yantsen.telegrambots.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.yantsen.telegrambots.configuration.TestDataConfiguration;
import ru.yantsen.telegrambots.entity.Product;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@Transactional
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void productRepositoryIsEmpty() {
        List<Product> products = productRepository.findAll();
        assertTrue(products.isEmpty());
    }

    @Test
    public void productRepositoryIsNotEmpty() {
        Product product = new Product();
        product.setModel("Product model TEST");
        productRepository.save(product);
        List<Product> products = productRepository.findAll();
        assertFalse(products.isEmpty());
    }
}
