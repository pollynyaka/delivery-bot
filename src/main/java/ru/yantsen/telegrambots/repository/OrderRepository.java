package ru.yantsen.telegrambots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yantsen.telegrambots.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
