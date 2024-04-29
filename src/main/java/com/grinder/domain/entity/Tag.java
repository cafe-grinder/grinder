package com.grinder.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "tag_name")
    private
}
