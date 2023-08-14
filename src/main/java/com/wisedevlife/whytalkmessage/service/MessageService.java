package com.wisedevlife.whytalkmessage.service;

import com.wisedevlife.whytalkmessage.dto.MessageRequest;
import com.wisedevlife.whytalkmessage.entity.Message;
import com.wisedevlife.whytalkmessage.repository.MessageRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @NonNull
    private final MessageRepository messageRepository;

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public void saveMessage(MessageRequest messageRequest) {
        Message messageSaved = messageRepository.save(messageRequest.toEntity());
        Marker marker = MarkerFactory.getMarker("saveMessage");
        logger.info(messageSaved.toString());
    }
}
