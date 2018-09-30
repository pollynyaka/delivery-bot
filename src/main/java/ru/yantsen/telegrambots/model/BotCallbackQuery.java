package ru.yantsen.telegrambots.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Lazy
@Component
@Scope("prototype")
public class BotCallbackQuery {

    @JsonProperty
    private String objectType;

    @JsonProperty
    private Integer objectId;

    @JsonProperty
    private String actionType;

    public String getObjectType() {
        return objectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public String getActionType() { return actionType; }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public static boolean isDataCorrect(BotCallbackQuery query) {
        return query != null
                && query.getObjectType() != null
                && query.getObjectId() != null
                && query.getActionType() != null;
    }

    @Override
    public String toString() {
        return "BotCallbackQuery{" +
                "objectType='" + objectType + '\'' +
                ", objectId=" + objectId +
                ", actionType='" + actionType + '\'' +
                '}';
    }
}
