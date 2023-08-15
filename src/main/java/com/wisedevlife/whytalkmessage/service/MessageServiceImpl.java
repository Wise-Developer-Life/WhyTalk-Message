package com.wisedevlife.whytalkmessage.service;

import com.wisedevlife.whytalkmessage.dto.MessageRequest;
import com.wisedevlife.whytalkmessage.entity.Message;
import com.wisedevlife.whytalkmessage.repository.MessageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;

@Slf4j
@Primary
@RequiredArgsConstructor
public class MessageServiceImpl {

    private final MessageRepository messageRepository;

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Message saveMessage(MessageRequest messageRequest) {
        return messageRepository.save(messageRequest.toEntity());
    }
}
