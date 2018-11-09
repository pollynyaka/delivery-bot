package ru.yantsen.telegrambots.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainBotController {

    @Value("${bot.username}")
    private String botUsername;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("botUsername", botUsername);
        return "index";
    }



}
