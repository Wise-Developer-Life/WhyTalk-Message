package com.wisedevlife.whytalkmessage.entity;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "from_user", nullable = false)
    private String fromUser;

    @Column(name = "to_user", nullable = false)
    private String toUser;

    @Column(name = "chat_room_id", nullable = false)
    private String chatRoomId;

    @Column(name = "send_time", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant sendDateTime;

    @Column(name = "read_time", columnDefinition = "TIMESTAMP")
    private Instant readDateTime;
}
