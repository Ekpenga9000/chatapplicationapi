package com.louiscodes.chatapplication.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="messages")
public class MessagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="message_id", nullable = false)
    private Long message_id;

    @NotEmpty
    @Column(name="message", nullable = false)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private AccountHolderEntity sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private AccountHolderEntity receiver;

    @Column(name="sent_at", nullable = false)
    private LocalDateTime sentAt;

    @Column(name="is_read", nullable = false)
    private boolean isRead;

}
