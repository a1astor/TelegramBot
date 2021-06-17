package com.test.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.test.model.MessageContent;

import java.util.Optional;

@Repository
public interface MessageContentRepository extends CrudRepository<MessageContent, Long> {

    Optional<MessageContent> findByContent(String content);

    void deleteByContent(String content);
}
