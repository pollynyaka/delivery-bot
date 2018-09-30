package ru.yantsen.telegrambots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yantsen.telegrambots.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
