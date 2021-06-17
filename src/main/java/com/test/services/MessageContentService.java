package com.test.services;

import com.test.model.MessageContent;

public interface MessageContentService {
    Iterable<MessageContent> findAll();

    MessageContent findById(Long id);

    MessageContent findByContent(String content);

    MessageContent save(String content);

    void deleteByName(String content);

    void deleteById(Long id);
}
