package ru.yantsen.telegrambots.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.yantsen.telegrambots.entity.Delivery;
import ru.yantsen.telegrambots.entity.Driver;
import ru.yantsen.telegrambots.entity.Status;
import ru.yantsen.telegrambots.exception.BotMessageException;
import ru.yantsen.telegrambots.service.CallbackQueryService;
import ru.yantsen.telegrambots.service.DeliveryService;
import ru.yantsen.telegrambots.service.DriverService;
import ru.yantsen.telegrambots.service.MessageProcessing;
import ru.yantsen.telegrambots.model.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class MessageProcessingImp implements MessageProcessing {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private CallbackQueryService callbackQueryService;



    @Override
    public List<SendMessage> showDeliveriesForDate(Long chatId, Date date, Status orderStatus) {
        Driver driver = driverService.getDriverByUserId(chatId);
        if (driver == null) {
            return Arrays.asList(createMessage(chatId, "Пользователь не найден"));
        }

        StringBuilder mainText = new StringBuilder();
        switch (orderStatus) {
            case ACCEPTED:
                mainText.append("Доставки");
                break;
            case COMPLETED:
                mainText.append("Выполненные доставки");
                break;
            case CANCELED:
                mainText.append("Отмененные доставки");
                break;
            default:
                break;
        }

        mainText.append(" для водителя: " + "<b>" + driver.getName() + "</b>" +
                "\nна: " + "<b>" + Constants.formatter.format(date) + "</b>");

        List<Delivery> deliveries = deliveryService.getDeliveriesByDateAndStatus(driver.getId(), date, orderStatus);
        List<SendMessage> result = new ArrayList<>();

        if (deliveries.isEmpty()) {
            mainText.append("\n---Доставок нет---");
            result.add(createMessage(chatId, mainText.toString()));
        } else {
            result.add(createMessage(chatId, mainText.toString()));
            createDeliveriesMessages(chatId, deliveries, result);
        }
        return result;
    }

    private void createDeliveriesMessages(Long chatId, List<Delivery> deliveries, List<SendMessage> result) {
        int i = 0;
        for (Delivery delivery : deliveries) {
            i++;
            SendMessage message;
            try {
                String data = callbackQueryService.createDeliveryCallbackData(delivery, Constants.ACTION_SHOW);
                String text = i + ". " + deliveryService.getDeliveryShortInfoAsString(delivery);

                message = createMessage(chatId, text);
                message.setReplyMarkup(createSingleInlineKeyboardButton("подробно", data));
            } catch (BotMessageException botExc) {
                message = createMessage(chatId, i + ". " + botExc.getMessage());
                System.out.println(botExc.getMessage() + "\n" + botExc.getExtraMessage());
            } catch (Exception e) {
                message = createMessage(chatId, i + ". " + BotMessageException.MSG_ERROR_DELIVERY_DATA);
                e.printStackTrace();
            }
            result.add(message);
        }
        result.add(createMessage(chatId, "<b>Всего доставок: " + deliveries.size() + " шт.</b>"));
    }


    public SendMessage showDefaultKeyboard(Long chatId) {
        SendMessage sendMessage = createMessage(chatId, "Вы в главном меню");

        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(Constants.ACCEPTED_DELIVERIES_FOR_TODAY);
        keyboard.add(keyboardRow1);

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(Constants.COMPLETED_DELIVERIES_FOR_TODAY);
        keyboard.add(keyboardRow2);

        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add(Constants.CANCELED_DELIVERIES_FOR_TODAY);
        keyboard.add(keyboardRow3);

        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }


    public SendMessage processCallbackQuery(Update update, Long chatId) {
        String result;
        try {
            result = callbackQueryService.parseData(update);
        } catch (BotMessageException botExc) {
            result = botExc.getMessage();
            System.out.println(botExc.getMessage() + "\n" + botExc.getExtraMessage());
        } catch (Exception exc) {
            result = BotMessageException.MSG_ERROR_DATA;
            exc.printStackTrace();
        }
        return createMessage(chatId, result);
    }


    private SendMessage createMessage(Long chatId, String text) {
        if (chatId == null || StringUtils.isEmpty(text))
            return null;

        return new SendMessage()
                .setChatId(chatId)
                .setText(text).setParseMode("HTML");
    }


    private InlineKeyboardMarkup createSingleInlineKeyboardButton(String text, String data) {
        if (text == null || data == null) return null;

        // Создаем клавиуатуру
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        // Создаем кнопку
        row.add(createInlineKeyboardButton(text, data));
        rowsInline.add(row);

        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }


    private InlineKeyboardButton createInlineKeyboardButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }


}
