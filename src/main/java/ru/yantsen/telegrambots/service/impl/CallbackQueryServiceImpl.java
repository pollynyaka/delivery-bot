package ru.yantsen.telegrambots.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yantsen.telegrambots.entity.Delivery;
import ru.yantsen.telegrambots.exception.BotMessageException;
import ru.yantsen.telegrambots.model.BotCallbackQuery;
import ru.yantsen.telegrambots.service.CallbackQueryService;
import ru.yantsen.telegrambots.service.DeliveryService;
import ru.yantsen.telegrambots.model.Constants;

import java.io.IOException;


@Service
public class CallbackQueryServiceImpl implements CallbackQueryService {

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    BotCallbackQuery query;

    @Override
    public String parseData(Update update) throws BotMessageException, IOException {
        if (update.getCallbackQuery() == null || update.getCallbackQuery().getData() == null) {
            throw new BotMessageException(BotMessageException.MSG_ERROR_DATA,
                    BotMessageException.MSG_ERROR_DATA_IS_EMPTY);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        BotCallbackQuery callbackQuery = objectMapper.
                readValue(update.getCallbackQuery().getData(), BotCallbackQuery.class);

        if (!BotCallbackQuery.isDataCorrect(callbackQuery))
            throw new BotMessageException(BotMessageException.MSG_ERROR_DATA,
                    BotMessageException.MSG_ERROR_INCORRECT_DATA);

        if (callbackQuery.getObjectType().equals(Constants.DELIVERY)
                && callbackQuery.getActionType().equals(Constants.ACTION_SHOW)) {
            String result = deliveryService.getDeliveryInfoAsString(callbackQuery.getObjectId());
            if (result == null)
                throw new BotMessageException(BotMessageException.MSG_ERROR_DELIVERY_DATA,
                        BotMessageException.MSG_ERROR_DELIVERY_NOT_FOUND);

            return result;
        }

        return null;
    }


    @Override
    public String createDeliveryCallbackData(Delivery delivery, String actionType) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider()
                .addFilter("none", SimpleBeanPropertyFilter.FilterExceptFilter.filterOutAllExcept(""));
        objectMapper.setFilterProvider(simpleFilterProvider);

        query.setObjectType(Constants.DELIVERY);
        query.setObjectId(delivery.getId());
        query.setActionType(actionType);
        return objectMapper.writeValueAsString(query);
    }




}
