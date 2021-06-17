package com.test.beans;

import com.test.services.BotService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bot extends TelegramLongPollingBot {

    @Value("${telegram.bot-name}")
    private String botName;

    @Value("${telegram.token}")
    private String token;

    private BotService botService;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Message inputMessage = update.getMessage();
        Long chatId = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage();
        if (inputMessage.getText() != null) {
            String botMessage = botService.generateBotMessage(inputMessage.getText());
            sendMessage.setText(botMessage);
            sendMessage.setChatId(chatId.toString());
            execute(sendMessage);
        }
    }

    @Autowired
    public void setBotService(BotService botService) {
        this.botService = botService;
    }
}
