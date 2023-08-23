package com.wisedevlife.whytalkmessage.common.exception.chatroom;

import com.wisedevlife.whytalkmessage.entity.ChatRoom;

public class ChatRoomExistedException extends RuntimeException {
    public ChatRoomExistedException(ChatRoom chatRoom) {
        super(String.format("ChatRoom existed. %s", chatRoom.toString()));
    }
}
