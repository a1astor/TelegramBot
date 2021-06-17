package com.test.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.test.model.Message;

import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    Optional<Message> findByCityIdAndMessageId(Long cityId, Long contentId);

    Optional<Message> findByCityId(Long id);
}
