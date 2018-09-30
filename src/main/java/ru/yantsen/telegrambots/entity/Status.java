package ru.yantsen.telegrambots.entity;

public enum Status {
    NEW("Новый"), ACCEPTED("Подтвержден"), COMPLETED("Выполнен"), CANCELED("Отменен");

    private String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

}
