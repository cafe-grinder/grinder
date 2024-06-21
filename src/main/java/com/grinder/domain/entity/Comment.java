package com.grinder.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "comment", indexes = {
        @Index(name = "idx_feed_id", columnList = "feed_id"), @Index(name = "idx_parent_comment_id", columnList = "parent_comment_id")
})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @Column(name = "comment_id", updatable = false, length = 36)
    private String commentId;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @ManyToOne
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "content", nullable = false, length = 200)
    private String content;

    @Column(name = "is_visible", nullable = false)
    private Boolean isVisible;

    @PrePersist
    public void prePersist() {
        commentId = commentId == null ? UUID.randomUUID().toString() : commentId;
        isVisible = isVisible == null ? true : isVisible;
    }

    public void updateContent(String content){
        this.content = content;
    }

    public void notVisible() {
        isVisible = false;
    }
}
