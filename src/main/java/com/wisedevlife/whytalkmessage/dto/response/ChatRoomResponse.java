package com.wisedevlife.whytalkmessage.dto.response;

import com.wisedevlife.whytalkmessage.entity.ChatRoom;

public record ChatRoomResponse(String chatRoomId) {
    public static ChatRoomResponse of(ChatRoom chatRoom) {
        return new ChatRoomResponse(chatRoom.getId().toString());
    }
}
