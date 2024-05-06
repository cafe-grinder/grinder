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
        private boolean isHeart;    // 사용자가 댓글을 좋아요 했는지 여부
        private int heartNum;       // 해당 댓글의 좋아요 수

        public FeedResponseDTO(Feed feed) {
            this.feedId = feed.getFeedId();
            this.memberNickname = feed.getMember().getNickname();
            this.memberEmail = feed.getMember().getEmail();
            this.cafeName = feed.getCafe().getName();
            this.content = feed.getContent();
            this.hits = feed.getHits();
            this.isVisible = feed.getIsVisible();
            this.grade = feed.getGrade();
            this.createdAt = feed.getCreatedAt();
            this.updatedAt = feed.getUpdatedAt();
            this.isHeart = false;
            this.heartNum = 0;
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FeedAndImageResponseDTO {
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
        private boolean isHeart;    // 사용자가 댓글을 좋아요 했는지 여부
        private int heartNum;       // 해당 댓글의 좋아요 수
        private List<String> image;

        public FeedAndImageResponseDTO(Feed feed, List<String> image) {
            this.feedId = feed.getFeedId();
            this.memberNickname = feed.getMember().getNickname();
            this.memberEmail = feed.getMember().getEmail();
            this.cafeName = feed.getCafe().getName();
            this.content = feed.getContent();
            this.hits = feed.getHits();
            this.isVisible = feed.getIsVisible();
            this.grade = feed.getGrade();
            this.createdAt = feed.getCreatedAt();
            this.updatedAt = feed.getUpdatedAt();
            this.isHeart = false;
            this.heartNum = 0;
            this.image = image;
        }
    }
}
