package com.wisedevlife.whytalkmessage.entity;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat_rooms")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChatRoom {
    @Id private ObjectId id;

    private String name;

    private List<String> userIds;

    private Date lastMessageSentAt;

    @CreatedDate private Date createdAt;

    @LastModifiedDate private Date updatedAt;
}
