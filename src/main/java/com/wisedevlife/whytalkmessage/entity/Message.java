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

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String fromUser;

    @Column(nullable = false)
    private String toUser;

    @Column(nullable = false)
    private String chatRoomId;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private Instant sendDateTime;

    @Column(columnDefinition = "TIMESTAMP")
    private Instant readDateTime;
}
