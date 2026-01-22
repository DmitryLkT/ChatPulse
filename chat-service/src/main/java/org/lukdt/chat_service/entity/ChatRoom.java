package org.lukdt.chat_service.entity;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chat_rooms")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long id;

    @Column(name = "name", nullable = false)
    String nameRoom;
    @Column(name = "created_by", nullable = false)
    String createdBy;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    LocalDateTime createAt;
    @Column(name = "update_at", nullable = false)
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(
            name = "chat_room_participants",
            joinColumns = @JoinColumn(name = "chat_room_id")
    )
    @Column(name = "user_id")
    List<Long> participants;

    @Enumerated(EnumType.STRING)
    TypeRoom type;

}
