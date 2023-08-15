package com.wisedevlife.whytalkmessage.dto;

import com.wisedevlife.whytalkmessage.entity.Message;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public record MessageRequest(
        String content,
        String fromUser,
        String toUser,
        String roomId,
        long sendDateTimestamp
        ) {
    public Message toEntity() {
        Instant sendDateTime = Instant.ofEpochMilli(sendDateTimestamp());
        System.out.println("sendDateTime: " + sendDateTime);
        return Message.builder()
                .content(this.content())
                .fromUser(this.fromUser())
                .toUser(this.toUser())
                .sendDateTime(sendDateTime)
                .build();
    }

}
