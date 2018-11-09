package ru.yantsen.telegrambots.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yantsen.telegrambots.repository.ProductRepository;
import ru.yantsen.telegrambots.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;


    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List getAllProducts() {
        return productRepository.findAll();
    }
}
