package com.wisedevlife.whytalkmessage.service;

import com.wisedevlife.whytalkmessage.dto.request.MessageRequest;
import com.wisedevlife.whytalkmessage.entity.Message;
import java.util.List;

public interface MessageService {
    List<Message> getMessages();

    Message saveMessage(MessageRequest messageRequest);
}
