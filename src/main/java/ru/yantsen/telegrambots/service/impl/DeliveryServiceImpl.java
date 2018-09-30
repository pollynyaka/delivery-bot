package ru.yantsen.telegrambots.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yantsen.telegrambots.entity.Delivery;
import ru.yantsen.telegrambots.entity.OrderItem;
import ru.yantsen.telegrambots.entity.Status;
import ru.yantsen.telegrambots.exception.BotMessageException;
import ru.yantsen.telegrambots.repository.DeliveryRepository;
import ru.yantsen.telegrambots.service.DeliveryService;

import java.util.Date;
import java.util.List;

import static ru.yantsen.telegrambots.exception.BotMessageException.MSG_ERROR_DATA;
import static ru.yantsen.telegrambots.exception.BotMessageException.MSG_ERROR_DELIVERY_NOT_FOUND;
import static ru.yantsen.telegrambots.model.Constants.formatter;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;


    @Override
    public List<Delivery> getDeliveriesByDateAndStatus(Integer driverId, Date date, Status status) {
        return deliveryRepository.findByDriverIdAndDeliveryDateAndOrder_Status(driverId, date, status);
    }

    @Override
    public String getDeliveryShortInfoAsString(Delivery delivery) throws Exception {
        if (delivery == null)
            throw new BotMessageException(MSG_ERROR_DATA, MSG_ERROR_DELIVERY_NOT_FOUND);

        return "Заказ: <b>" + delivery.getOrder().getOrderNumber() + "</b>" +
                "\nАдрес: " + delivery.getOrder().getAddress();
    }


    @Override
    public String getDeliveryInfoAsString(Integer deliveryId) {
        Delivery delivery = getDeliveryById(deliveryId);
        return getDeliveryInfoAsString(delivery);
    }


    private String getDeliveryInfoAsString(Delivery delivery) {
        if (delivery == null) return null;

        String str = "Заказ: <b>" + delivery.getOrder().getOrderNumber() + "</b>" +
                "\nДата доставки: <b>" + formatter.format(delivery.getDate()) + "</b>" +
                "\nПолучатель: <b>" + delivery.getOrder().getClientName() + "</b>" +
                "\nАдрес: <b>" + delivery.getOrder().getAddress() + "</b>" +
                "\nСтатус: <b>" + delivery.getOrder().getStatus() + "</b>" +
                "\nПозиции заказа:";

        int i = 1;
        for (OrderItem item : delivery.getOrder().getItems()) {
            str = str + "\n" + i++ + ". " + item.getProduct().getModel();
        }
        str = str + "\n<b>Всего позиций: " + delivery.getOrder().getItems().size() + " шт.</b>";

        return str;
    }

    private Delivery getDeliveryById(Integer id) {
        return deliveryRepository.findById(id).orElse(null);
    }


}
