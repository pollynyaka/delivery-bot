package ru.yantsen.telegrambots.exception;

public class BotMessageException extends Exception {

    private String extraMessage = "";

    public BotMessageException() {
        super();
    }

    public BotMessageException(String message) {
        super(message);
    }

    public BotMessageException(String message, String _extraMessage) {
        super(message);
        extraMessage = _extraMessage;
    }

    public String getExtraMessage() {
        return extraMessage;
    }


    public BotMessageException(Throwable cause) {
        super(cause);
    }

    public BotMessageException(String message, Throwable cause) {
        super(message, cause);
    }


    public static String MSG_ERROR_DATA = "ошибка обработки данных";

    public static String MSG_ERROR_DATA_IS_EMPTY = "данные отсутствуют";
    public static String MSG_ERROR_INCORRECT_DATA = "некорректные данные";

    public static String MSG_ERROR_DELIVERY_DATA = "ошибка получения информации о доставке";
    public static String MSG_ERROR_DELIVERY_NOT_FOUND = "доставка не найдена";

}
