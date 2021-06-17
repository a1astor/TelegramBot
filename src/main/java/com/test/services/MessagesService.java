package com.test.services;

import com.test.model.Message;

public interface MessagesService {
    Iterable<Message> findAll();

    Message findById(Long id);

    Message create(Message message);

    void deleteById(Long id);
}
