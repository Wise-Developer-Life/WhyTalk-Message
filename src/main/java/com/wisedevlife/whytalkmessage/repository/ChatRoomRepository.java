package com.wisedevlife.whytalkmessage.repository;

import com.wisedevlife.whytalkmessage.entity.ChatRoom;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, ObjectId> {

    @Aggregation(pipeline = {
            "{ $match: { userIds: ?0 } }",
            "{ $sort: { lastMessageSentAt: -1 } }",
            "{ $skip: ?1 }",
            "{ $limit: ?2 }"
    })
    List<ChatRoom> findLatestChatRoomsOfUserIdLimitedToWithOffset(String userId, int skip, int limit);

    @Query(value = "{ userIds: { $all: [ ?0, ?1 ] } }", sort = "{ lastMessageSentAt: -1 }")
    Optional<ChatRoom> findOneToOneChatRoom(String user1, String user2);

    Integer countChatRoomsByUserIdsContains(String userId);
}
