package com.wisedevlife.whytalkmessage.dto;

import com.wisedevlife.whytalkmessage.entity.Message;

import java.time.Instant;
import java.time.LocalDateTime;

public record MessageRequest(
        String content,
        String fromUser,
        String toUser,
        long sendDateTimestamp
        ) {
    public Message toEntity() {
        Instant sendDateTime = Instant.ofEpochSecond(sendDateTimestamp());
        return Message.builder()
                .content(this.content())
                .fromUser(this.fromUser())
                .toUser(this.toUser())
                .sendDateTime(sendDateTime)
                .build();
    }

}
