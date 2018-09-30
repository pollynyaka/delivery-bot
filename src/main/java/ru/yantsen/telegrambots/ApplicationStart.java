package ru.yantsen.telegrambots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);

        while (true) {
            try {
                Thread.sleep(80000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
