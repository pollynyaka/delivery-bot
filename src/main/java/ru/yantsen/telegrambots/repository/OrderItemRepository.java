package ru.yantsen.telegrambots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yantsen.telegrambots.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
