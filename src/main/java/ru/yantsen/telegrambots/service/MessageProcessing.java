package ru.yantsen.telegrambots.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yantsen.telegrambots.entity.Status;

import java.util.Date;
import java.util.List;

public interface MessageProcessing {

    SendMessage showDefaultKeyboard(Long chatId);

    SendMessage processCallbackQuery(Update update, Long chatId);

    List<SendMessage> showDeliveriesForDate(Long chatId, Date date, Status orderStatus);


}
