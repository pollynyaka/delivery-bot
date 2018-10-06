package ru.yantsen.telegrambots.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainDeliveryBotController {

    @Value("${bot.username}")
    private String botUsername;


    @GetMapping("/")
    public String showBotName() {
        return "Telegram delivery bot = " + botUsername;
    }

    @RequestMapping("/{word}")
    String hello(@PathVariable String word) {
        return "\"" + word + "\" - that's funny!";
    }


}
