package com.test.controllers;

import com.test.model.MessageContent;
import com.test.services.MessageContentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/message-content")
@RequiredArgsConstructor
public class MessageContentController {

    private MessageContentService messageContentService;

    @ApiOperation(value = "Find all messages")
    @GetMapping("/")
    public Iterable<MessageContent> findAll() {
        return messageContentService.findAll();
    }

    @NotNull
    @Pattern(regexp = "#^[0-9]+$#")
    @ApiOperation(value = "Find message content by id")
    @GetMapping("/byId{id}")
    public MessageContent findMessageContent(@PathVariable Long id) {
        return messageContentService.findById(id);
    }

    @NotNull
    @NotBlank
    @ApiOperation(value = "Find message content by content")
    @Transactional
    @GetMapping("/byContent{content}")
    public MessageContent findMessageContentByContent(@PathVariable String content) {
        return messageContentService.findByContent(content);
    }

    @NotBlank
    @NotNull
    @ApiOperation(value = "Creating message")
    @PostMapping("/save")
    public MessageContent create(@PathVariable String content) {
        return messageContentService.save(content);
    }

    @NotNull
    @NotBlank
    @ApiOperation(value = "Remove message content")
    @Transactional
    @DeleteMapping("/remove{content}")
    public void removeMessageContent(@PathVariable String content) {
        messageContentService.deleteByName(content);
    }

    @NotNull
    @Pattern(regexp = "#^[0-9]+$#")
    @ApiOperation(value = "Remove message content by id")
    @Transactional
    @DeleteMapping("/removeById{id}")
    public void removeCityById(@PathVariable Long id) {
        messageContentService.deleteById(id);
    }

    @Autowired
    public void setCitiesService(MessageContentService messageContentService) {
        this.messageContentService = messageContentService;
    }
}
