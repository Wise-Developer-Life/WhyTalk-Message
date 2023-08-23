package com.wisedevlife.whytalkmessage.repository;

import com.wisedevlife.whytalkmessage.entity.ChatRoom;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, ObjectId> {
    Page<ChatRoom> findChatRoomsByUserIdsContaining(String userId, Pageable pageable);

    @Query(value = "{ 'userIds': { $all: [ ?0, ?1 ] } }", sort = "{ 'createdAt': 1 }")
    Optional<ChatRoom> findOneToOneChatRoom(String user1, String user2);
}
