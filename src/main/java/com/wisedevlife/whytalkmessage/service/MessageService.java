package com.wisedevlife.whytalkmessage.service;

import com.wisedevlife.whytalkmessage.dto.MessageRequest;
import com.wisedevlife.whytalkmessage.entity.Message;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    List<Message> getMessages();

    Message saveMessage(MessageRequest messageRequest);
}
