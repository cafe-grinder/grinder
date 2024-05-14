package com.grinder.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "message")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity {

    @Id
    @Column(name = "message_id", updatable = false, length = 36)
    private String messageId;

    @ManyToOne
    @JoinColumn(name = "send_member_id")
    private Member sendMember;

    @ManyToOne
    @JoinColumn(name = "receive_member_id", nullable = false)
    private Member receiveMember;

    @Column(name = "content", nullable = false, length = 2000)
    private String content;

    @Column(name = "is_checked", nullable = false)
    private Boolean isChecked;

    @Column(name = "admin_name")
    private String adminName;


    @PrePersist
    public void prePersist() {
        messageId = messageId == null ? UUID.randomUUID().toString() : messageId;
        isChecked = isChecked == null ? false : isChecked;
    }
}
