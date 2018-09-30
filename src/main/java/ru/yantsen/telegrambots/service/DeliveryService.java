package ru.yantsen.telegrambots.service;

import ru.yantsen.telegrambots.entity.Delivery;
import ru.yantsen.telegrambots.entity.Status;
import ru.yantsen.telegrambots.exception.BotMessageException;

import java.util.Date;
import java.util.List;

public interface DeliveryService {

    List getDeliveriesByDateAndStatus(Integer driverId, Date date, Status status);

    String getDeliveryShortInfoAsString(Delivery delivery) throws Exception;

    String getDeliveryInfoAsString(Integer deliveryId) throws BotMessageException;

}
