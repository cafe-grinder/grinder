package com.grinder.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "feed")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feed extends BaseEntity {

    @Id
    @Column(name = "feed_id", updatable = false, length = 36)
    private String feedId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    @Column(name = "content")
    private String content;

    @Column(name = "hits")
    private Long hits;

    @Column(name = "is_visible")
    private Boolean isVisible;

    @Column(name = "grade")
    private Long grade;

    @PrePersist
    public void prePersist() {
        feedId = feedId == null ? UUID.randomUUID().toString() : feedId;
        hits = hits == null ? 0 : hits;
        isVisible = isVisible == null ? true : isVisible;
    }
}
