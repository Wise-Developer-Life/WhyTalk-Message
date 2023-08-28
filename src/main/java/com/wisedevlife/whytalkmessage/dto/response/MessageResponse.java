package com.wisedevlife.whytalkmessage.dto.response;

import com.wisedevlife.whytalkmessage.entity.Message;
import lombok.Builder;

import java.time.Instant;

@Builder
public record MessageResponse(
        long messageId,
        String content,
        String fromUser,
        String toUser,
        String chatRoomId,
        Long sendDateTimestamp,
        Long readDateTimestamp) {
    public static MessageResponse toMessageResponse(Message message) {
        long sendTimeStamps = message.getSendDateTime().getEpochSecond();
        Instant readTime = message.getReadDateTime();
        Long readTimestamp = readTime != null ? readTime.getEpochSecond() : null;
        return MessageResponse.builder()
                .messageId(message.getId())
                .chatRoomId(message.getChatRoomId())
                .fromUser(message.getFromUser())
                .toUser(message.getToUser())
                .content(message.getContent())
                .sendDateTimestamp(sendTimeStamps)
                .readDateTimestamp(readTimestamp)
                .build();
    }
}
