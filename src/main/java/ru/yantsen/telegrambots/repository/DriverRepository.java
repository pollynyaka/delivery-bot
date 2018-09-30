package ru.yantsen.telegrambots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yantsen.telegrambots.entity.Driver;


public interface DriverRepository extends JpaRepository<Driver, Integer> {

    Driver findByUserId(Long userId);
}

