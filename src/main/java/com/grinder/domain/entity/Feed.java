package com.grinder.domain.entity;

import com.grinder.domain.dto.FeedDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "feed", indexes = {
        @Index(name = "idx_cafe_id", columnList = "cafe_id"),
        @Index(name = "idx_visible_updatedAt_feedId", columnList = "is_visible, updated_at, feed_id")
})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feed extends BaseEntity {

    @Id
    @Column(name = "feed_id", updatable = false, length = 36)
    private String feedId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @Column(name = "content", nullable = false, length = 2000)
    private String content;

    @Column(name = "is_visible", nullable = false)
    private Boolean isVisible;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "rank")
    private Integer rank;

    @PrePersist
    public void prePersist() {
        feedId = feedId == null ? UUID.randomUUID().toString() : feedId;
        isVisible = isVisible == null ? true : isVisible;
        rank = rank == null ? 0 : rank;
    }

    public void updateFeed(Cafe cafe, String content, Integer grade) {
        this.cafe = cafe;
        this.content = content;
        this.grade = grade;
    }

    public void notVisible() {
        isVisible = false;
    }
}
