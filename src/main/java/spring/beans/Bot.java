package spring.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import spring.Services;
import spring.model.Town;

import java.util.Optional;

@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private Services service;

    @Override
    public String getBotUsername() {
        return  "CitiesInfo_bot";
    }

    @Override
    public String getBotToken() {
        return "1895037071:AAH2hag5f-PbBYpq_zr3Yzv4wGJ9yyYIp_4";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Long chatId = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage();
        if (message.getText() != null) {
            Optional<Town> townName = service.getTownRepository().findByName(message.getText());
            if (townName.isPresent()) {
                sendMessage.setText(townName.get().getName());
            } else {
                sendMessage.setText("введите другой город");
            }
            sendMessage.setChatId(chatId.toString());
            execute(sendMessage);
        }
    }


    @Autowired
    public void setService(Services service) {
        this.service = service;
    }
}
