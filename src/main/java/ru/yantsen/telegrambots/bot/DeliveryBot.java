package ru.yantsen.telegrambots.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yantsen.telegrambots.entity.Status;
import ru.yantsen.telegrambots.model.Constants;
import ru.yantsen.telegrambots.service.MessageProcessing;

import java.util.Date;
import java.util.List;

public class DeliveryBot extends TelegramLongPollingBot {

    @Autowired
    private MessageProcessing messageService;

    private String botToken;

    private String botUsername;

    public DeliveryBot() {
    }

    public DeliveryBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId;
        if (update.hasMessage() && update.getMessage().hasText()) {
            String incomingText = update.getMessage().getText();
            chatId = update.getMessage().getChatId();

            if ("/start".equalsIgnoreCase(incomingText)) {
                sendAnswer(messageService.showDefaultKeyboard(chatId));
            } else if (Constants.ACCEPTED_DELIVERIES_FOR_TODAY.equalsIgnoreCase(incomingText)) {
                sendDeliveries(chatId, new Date(), Status.ACCEPTED);
            } else if (Constants.COMPLETED_DELIVERIES_FOR_TODAY.equalsIgnoreCase(incomingText)) {
                sendDeliveries(chatId, new Date(), Status.COMPLETED);
            } else if (Constants.CANCELED_DELIVERIES_FOR_TODAY.equalsIgnoreCase(incomingText)) {
                sendDeliveries(chatId, new Date(), Status.CANCELED);
            }
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            sendAnswer(messageService.processCallbackQuery(update, chatId));
        }
    }

    private void sendDeliveries(Long chatId, Date date, Status orderStatus) {
        List<SendMessage> messages = messageService.showDeliveriesForDate(chatId, date, orderStatus);
        for (SendMessage message : messages) {
            sendAnswer(message);
        }
    }


    private void sendAnswer(Object response) {
        if (response == null) return;
        try {
            if (response instanceof SendMessage) {
                execute((SendMessage) response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public void setBotUsername(String botUsername) {
        this.botUsername = botUsername;
    }

    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }


}
