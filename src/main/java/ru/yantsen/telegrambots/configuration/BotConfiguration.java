package ru.yantsen.telegrambots.configuration;


import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.yantsen.telegrambots.bot.DeliveryBot;

@Configuration
public class BotConfiguration {

    @Value("${proxy.host}")
    private String proxy_host;

    @Value("${proxy.port}")
    private String proxy_port;

    @Value("${bot.token}")
    private String botToken;

    @Value("${bot.username}")
    private String botUsername;

    @Bean
    public DeliveryBot registerBot() {
        System.out.println("Bot creating ... ");

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            DeliveryBot bot = new DeliveryBot();
            bot.setBotToken(botToken);
            bot.setBotUsername(botUsername);
            botsApi.registerBot(bot);

            System.out.println("Bot created");
            return bot;
        } catch (Exception e) {
            e.printStackTrace();
            DeliveryBot bot = useProxy(botsApi);
            return bot;
        }
    }

    private DeliveryBot useProxy(TelegramBotsApi botsApi) {
        HttpHost proxy = new HttpHost(proxy_host, Integer.valueOf(proxy_port));
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        RequestConfig rc = RequestConfig.custom().setProxy(proxy).build();
        botOptions.setRequestConfig(rc);
        DeliveryBot bot = new DeliveryBot(botOptions);
        bot.setBotToken(botToken);
        bot.setBotUsername(botUsername);
        try {
            botsApi.registerBot(bot);
            System.out.println("Bot created");
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
            System.out.println("ERROR: Failed to register delivery bot. Application is closed.");
            System.exit(1);
        }
        return bot;
    }

}
