package com.wisedevlife.whytalkmessage.service.impl;

import com.wisedevlife.whytalkmessage.dto.request.MessageRequest;
import com.wisedevlife.whytalkmessage.entity.Message;
import com.wisedevlife.whytalkmessage.repository.MessageRepository;
import com.wisedevlife.whytalkmessage.service.MessageService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message saveMessage(MessageRequest messageRequest) {
        return messageRepository.save(messageRequest.toEntity());
    }
}
