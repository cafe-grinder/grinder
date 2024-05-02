package com.grinder.domain.dto;

import com.grinder.domain.entity.Feed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class FeedDTO {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FeedRequestDTO {
        // 피드 내용, 이미지, 카페, 평점, 태그
        private String cafeId;
        private String content;
        private List<String> imageUrlList;
        private List<String> tagNameList;
        private Integer grade;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FeedResponseDTO {
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
        private boolean isHeart;
        private int heartNum;

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
}
