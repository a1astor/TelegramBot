package com.test.services.impl;

import com.test.dao.CitiesRepository;
import com.test.dao.MessageContentRepository;
import com.test.dao.MessageRepository;
import com.test.model.City;
import com.test.model.Message;
import com.test.model.MessageContent;
import com.test.services.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BotServiceImpl implements BotService {

    private final static String NO_CONTENT = "Нет описания к введенному городу";

    private final static String BO_CITY = "введите другой город";

    private CitiesRepository citiesRepository;

    private MessageRepository messageRepository;

    private MessageContentRepository messageContentRepository;


    public String generateBotMessage(String userMessage) {
        Optional<City> cityByName = citiesRepository.findByName(userMessage);
        return cityByName.isPresent() ? getMessageContentByCity(cityByName.get()) : BO_CITY;
    }

    private String getMessageContentByCity(City city) {
        Optional<com.test.model.Message> message = messageRepository.findByCityId(city.getId());
        return message.isPresent() ? getContentFromMessage(message) : NO_CONTENT;
    }

    private String getContentFromMessage(Optional<Message> message) {
        Optional<MessageContent> content = messageContentRepository.findById(message.get().getContentId());
        return content.isPresent() ? content.get().getContent() : NO_CONTENT;
    }

    @Autowired
    public void setCitiesRepository(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired
    public void setMessageContentRepository(MessageContentRepository messageContentRepository) {
        this.messageContentRepository = messageContentRepository;
    }
}
