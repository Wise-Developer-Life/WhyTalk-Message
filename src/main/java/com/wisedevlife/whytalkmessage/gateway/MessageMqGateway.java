package com.wisedevlife.whytalkmessage.gateway;

import com.wisedevlife.whytalkmessage.dto.MessageMqRequest;
import com.wisedevlife.whytalkmessage.dto.MessageRequest;
import com.wisedevlife.whytalkmessage.dto.MqMessagePayload;
import com.wisedevlife.whytalkmessage.entity.Message;
import com.wisedevlife.whytalkmessage.service.MessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class MessageMqGateway {
    private final MessageService messageService;

    public MessageMqGateway(MessageService messageService) {
        this.messageService = messageService;
    };

    @RabbitListener(queues = "message.queue")
    public void saveMessageToDatabase(MqMessagePayload<MessageMqRequest> message) {
        log.info("Message received from RabbitMQ: {} with message pattern {}", message.data(), message.eventPattern());

        MessageMqRequest messageMqRequest = message.data();
        Message savedMessage = messageService.saveMessage(new MessageRequest(
                messageMqRequest.content(),
                messageMqRequest.fromUser(),
                messageMqRequest.toUser(),
                messageMqRequest.roomId(),
                messageMqRequest.sendDateTimestamp()
        ));

        log.info("Message saved to database: {}", savedMessage);

    }
}
