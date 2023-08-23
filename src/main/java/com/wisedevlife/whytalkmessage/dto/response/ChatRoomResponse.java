package com.wisedevlife.whytalkmessage.dto.response;

import com.wisedevlife.whytalkmessage.entity.ChatRoom;

import java.util.List;

public record ChatRoomResponse(
        String id,
        List<String> users
) {
    public static ChatRoomResponse toChatRoomResponse(ChatRoom chatRoom) {
        return new ChatRoomResponse(
                chatRoom.getId().toString(),
                chatRoom.getUserIds()
        );
    }
}
