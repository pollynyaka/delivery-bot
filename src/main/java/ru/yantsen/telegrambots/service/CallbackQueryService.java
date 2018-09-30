package ru.yantsen.telegrambots.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yantsen.telegrambots.entity.Delivery;

public interface CallbackQueryService {

    String parseData(Update update) throws Exception;

    String createDeliveryCallbackData(Delivery delivery, String actionType) throws JsonProcessingException;

}
