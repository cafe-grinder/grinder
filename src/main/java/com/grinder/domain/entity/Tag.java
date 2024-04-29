package com.grinder.domain.entity;

import com.grinder.domain.enums.TagName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "tag")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @Column(name = "tag_id", updatable = false, length = 36)
    private String tagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @Enumerated(EnumType.STRING)
    @Column(name = "tag_name")
    private TagName tagName;

    @PrePersist
    public void prePersist() {
        tagId = tagId == null ? UUID.randomUUID().toString() : tagId;
    }
}
