package ru.yantsen.telegrambots.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yantsen.telegrambots.entity.Delivery;
import ru.yantsen.telegrambots.entity.Status;

import java.util.Date;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    List<Delivery> findByDriverIdAndDeliveryDateAndOrder_Status(Integer driverId, Date date, Status status);

}
