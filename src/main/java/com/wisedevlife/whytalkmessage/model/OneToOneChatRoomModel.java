package com.wisedevlife.whytalkmessage.model;

import com.wisedevlife.whytalkmessage.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OneToOneChatRoomModel {
    private String roomId;
    private UserModel otherUser;
    private Message lastMessage;
}
