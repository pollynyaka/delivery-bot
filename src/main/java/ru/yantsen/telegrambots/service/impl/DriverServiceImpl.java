package ru.yantsen.telegrambots.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yantsen.telegrambots.entity.Driver;
import ru.yantsen.telegrambots.repository.DriverRepository;
import ru.yantsen.telegrambots.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

    private DriverRepository driverRepository;


    @Autowired
    public void setDriverRepository(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    @Override
    public Driver getDriverByUserId(Long userId) {
        if (userId == null) return null;

        return driverRepository.findByUserId(userId);
    }


}
