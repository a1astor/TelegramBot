package com.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import com.test.beans.Bot;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.test")
public class Application {

    public static void main(String[] args) throws TelegramApiException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        TelegramBotsApi telegram = new TelegramBotsApi(DefaultBotSession.class);
        Bot bean = context.getBean(Bot.class);
        telegram.registerBot(bean);
    }
}
