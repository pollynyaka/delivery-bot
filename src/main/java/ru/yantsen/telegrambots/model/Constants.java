package ru.yantsen.telegrambots.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constants {

    public static final String ACCEPTED_DELIVERIES_FOR_TODAY = "Доставки на сегодня";
    public static final String CANCELED_DELIVERIES_FOR_TODAY = "Отмененные доставки на сегодня";
    public static final String COMPLETED_DELIVERIES_FOR_TODAY = "Выполненные доставки на сегодня";

    public static final String DELIVERY = "delivery";
    public static final String ACTION_SHOW = "show";

    public static final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
}
