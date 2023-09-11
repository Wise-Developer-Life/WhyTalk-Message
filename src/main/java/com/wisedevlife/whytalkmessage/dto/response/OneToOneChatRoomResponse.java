package com.wisedevlife.whytalkmessage.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wisedevlife.whytalkmessage.model.OneToOneChatRoomModel;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "one to one chat room response")
public record OneToOneChatRoomResponse(
        @Schema(description = "chat room id") @JsonProperty("chatRoomId") String id,
        @Schema(description = "last message") @JsonProperty("lastMessage")
                MessageResponse lastMessage,
        @Schema(description = "room of user") @JsonProperty("user") UserProfileResponse otherUser) {
    public static OneToOneChatRoomResponse of(OneToOneChatRoomModel chatRoomModel) {
        return new OneToOneChatRoomResponse(
                chatRoomModel.getRoomId(),
                MessageResponse.toMessageResponse(chatRoomModel.getLastMessage()),
                UserProfileResponse.of(chatRoomModel.getOtherUser()));
    }
}
