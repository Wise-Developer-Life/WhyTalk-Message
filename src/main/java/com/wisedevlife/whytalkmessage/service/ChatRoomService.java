package com.wisedevlife.whytalkmessage.service;

import com.wisedevlife.whytalkmessage.entity.ChatRoom;
import com.wisedevlife.whytalkmessage.model.OneToOneChatRoomModel;
import java.util.List;

public interface ChatRoomService {
    ChatRoom createOneToOneChatRoom(String user1, String user2);

    List<OneToOneChatRoomModel> getChatRooms(String userId, int offset, int limit);

    int getNumberOfChatRoomsByUserId(String userId);

    void deleteChatRoom(String roomId);
}
