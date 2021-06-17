package com.test.services.impl;

import com.test.dao.CitiesRepository;
import com.test.dao.MessageContentRepository;
import com.test.dao.MessageRepository;
import com.test.exceptions.ItemAlreadyExistException;
import com.test.exceptions.NoSuchCityException;
import com.test.exceptions.NoSuchMessageException;
import com.test.model.City;
import com.test.model.Message;
import com.test.model.MessageContent;
import com.test.services.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessagesServiceImpl implements MessagesService {

    private MessageRepository messageRepository;
    private MessageContentRepository messageContentRepository;
    private CitiesRepository citiesRepository;

    @Override
    public Iterable<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message findById(Long id) throws NoSuchMessageException {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isEmpty()) {
            throw new NoSuchMessageException();
        } return message.get();
    }

    @Override
    public void deleteById(Long id) throws NoSuchCityException {
        Optional<Message> deletingMessage = messageRepository.findById(id);
        if (deletingMessage.isEmpty()) {
            throw new NoSuchCityException();
        }
        messageRepository.deleteById(id);
    }

    @Override
    public Message create(Message message) throws NoSuchCityException, NoSuchMessageException,
            ItemAlreadyExistException {
        Optional<City> city = citiesRepository.findById(message.getCityId());
        if (city.isEmpty()) {
            throw new NoSuchCityException();
        }

        Optional<MessageContent> content = messageContentRepository.findById(message.getContentId());
        if (content.isEmpty()) {
            throw new NoSuchMessageException();
        }

        Optional<Message> newMessage = messageRepository.findByCityIdAndMessageId(message.getCityId(),
                message.getContentId());
        if (newMessage.isPresent()) {
            throw new ItemAlreadyExistException();
        }
        return messageRepository.save(message);
    }

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired
    public void setMessageContentRepository(MessageContentRepository messageContentRepository) {
        this.messageContentRepository = messageContentRepository;
    }

    @Autowired
    public void setCitiesRepository(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }
}
