package com.grinder.domain.dto;

import com.grinder.domain.entity.Feed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedResponseDTO {
    private String feedId;
    private String memberNickname;
    private String memberEmail;
    private String cafeName;
    private String content;
    private Integer hits;
    private Boolean isVisible;
    private Integer grade;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedResponseDTO(Feed feed) {
        feedId = feed.getFeedId();
        memberNickname = feed.getMember().getNickname();
        memberEmail = feed.getMember().getEmail();
        cafeName = feed.getCafe().getName();
        content = feed.getContent();
        hits = feed.getHits();
        isVisible = feed.getIsVisible();
        grade = feed.getGrade();
        createdAt = feed.getCreatedAt();
        updatedAt = feed.getUpdatedAt();
    }
}
