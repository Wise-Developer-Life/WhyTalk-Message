package com.wisedevlife.whytalkmessage.service.impl;

import com.wisedevlife.whytalkmessage.dto.request.MessageRequest;
import com.wisedevlife.whytalkmessage.entity.Message;
import com.wisedevlife.whytalkmessage.repository.MessageRepository;
import com.wisedevlife.whytalkmessage.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public Page<Message> getPagedMessagesByChatRoomId(
            String chatRoomId, long lastMessageId, int limit) {
        return messageRepository.findMessagesInChatRoom(
                chatRoomId, lastMessageId, PageRequest.of(0, limit));
    }

    @Override
    public Message saveMessage(MessageRequest messageRequest) {
        return messageRepository.save(messageRequest.toEntity());
    }
}
