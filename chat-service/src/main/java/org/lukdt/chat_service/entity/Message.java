package org.lukdt.chat_service.entity;

import jakarta.persistence.*;

import lombok.*;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long id;

    @Column(name = "sender_id", nullable = false)
    Long senderId;
    @Column(name = "content", nullable = false)
    String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    ChatRoom room;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name = "edited", nullable = false)
    boolean edited;
    @Column(name = "read", nullable = false)
    boolean read;
}
