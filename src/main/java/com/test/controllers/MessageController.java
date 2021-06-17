package com.test.controllers;

import com.test.model.Message;
import com.test.services.MessagesService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private MessagesService messagesService;

    @ApiOperation(value = "Find all messages")
    @GetMapping("/")
    public Iterable<Message> findAll() {
        return messagesService.findAll();
    }

    @NotNull
    @Pattern(regexp = "#^[0-9]+$#")
    @ApiOperation(value = "Find message by id")
    @GetMapping("/{id}")
    public Message findMessageById(@PathVariable Long id) {
        return  messagesService.findById(id);
    }

    @NotNull
    @ApiOperation(value = "Creating message by town id and content id ")
    @PostMapping("/save")
    public Message createMessage(@RequestBody Message message) {
        return messagesService.create(message);
    }

    @NotNull
    @Pattern(regexp = "#^[0-9]+$#")
    @ApiOperation(value = "Remove message")
    @Transactional
    @DeleteMapping("/remove{id}")
    public void removeMessage(@PathVariable Long id) {
        messagesService.deleteById(id);
    }


    @Autowired
    public void setCitiesService(MessagesService messagesService) {
        this.messagesService = messagesService;
    }
}
