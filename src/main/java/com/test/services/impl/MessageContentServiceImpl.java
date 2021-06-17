package com.test.services.impl;

import com.test.dao.MessageContentRepository;
import com.test.exceptions.ItemAlreadyExistException;
import com.test.exceptions.NoSuchMessageContentException;
import com.test.model.MessageContent;
import com.test.services.MessageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageContentServiceImpl implements MessageContentService {

    private MessageContentRepository repository;

    @Override
    public Iterable<MessageContent> findAll() {
        return repository.findAll();
    }

    @Override
    public MessageContent findById(Long id) throws NoSuchMessageContentException {
        Optional<MessageContent> message = repository.findById(id);
        if (message.isEmpty()) {
            throw new NoSuchMessageContentException();
        }
        return message.get();
    }

    @Override
    public MessageContent findByContent(String content) throws NoSuchMessageContentException {
        Optional<MessageContent> message = repository.findByContent(content);
        if (message.isEmpty()) {
            throw new NoSuchMessageContentException();
        }
        return message.get();
    }

    @Override
    public MessageContent save(String messageContent) throws ItemAlreadyExistException {
        Optional<MessageContent> messageToAdd = repository.findByContent(messageContent);
        if (messageToAdd.isPresent()) {
            throw new ItemAlreadyExistException();
        }
        return repository.save(new MessageContent(null, messageContent));
    }

    @Override
    public void deleteByName(String content) throws NoSuchMessageContentException {
        Optional<MessageContent> deletingMessageContent = repository.findByContent(content);
        if (deletingMessageContent.isEmpty()) {
            throw new NoSuchMessageContentException();
        }
        repository.deleteByContent(content);
    }

    @Override
    public void deleteById(Long id) throws NoSuchMessageContentException {
        Optional<MessageContent> deletingMessageContent = repository.findById(id);
        if (deletingMessageContent.isEmpty()) {
            throw new NoSuchMessageContentException();
        }
        repository.deleteById(id);
    }

    @Autowired
    public void setRepository(MessageContentRepository repository) {
        this.repository = repository;
    }
}
