package com.wisedevlife.whytalkmessage.service;

import com.wisedevlife.whytalkmessage.entity.ChatRoom;
import org.springframework.data.domain.Page;

public interface ChatRoomService {
    ChatRoom createOneToOneChatRoom(String user1, String user2);
    Page<ChatRoom> getChatRooms(String userId, int offset, int limit);
    void deleteChatRoom(String roomId);
}
