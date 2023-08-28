package com.wisedevlife.whytalkmessage.repository;

import com.wisedevlife.whytalkmessage.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.chatRoomId = :chatRoomId  AND m.id <= :lastMessageId ORDER BY m.id DESC")
    Page<Message> findMessagesInChatRoom(String chatRoomId, long lastMessageId, Pageable pageable);
}
