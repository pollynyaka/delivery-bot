package ru.yantsen.telegrambots.service;

import ru.yantsen.telegrambots.entity.Driver;

public interface DriverService {

    Driver getDriverByUserId(Long userId);
}
