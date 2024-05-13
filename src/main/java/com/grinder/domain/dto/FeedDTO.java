package com.grinder.domain.dto;

import com.grinder.domain.entity.*;
import com.grinder.domain.enums.TagName;
import com.grinder.service.ImageService;
import com.grinder.service.TagService;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        private Boolean isVisible;
        private Integer grade;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<String> tagNameList;
        private List<String> imageUrlList;
        private List<CommentDTO.ParentCommentResponseDTO> parentCommentList;
        private boolean isHeart;    // 사용자가 댓글을 좋아요 했는지 여부
        private int heartNum;       // 해당 댓글의 좋아요 수

        public FeedResponseDTO(Feed feed) {
            this.feedId = feed.getFeedId();
            this.memberNickname = feed.getMember().getNickname();
            this.memberEmail = feed.getMember().getEmail();
            this.cafeName = feed.getCafe() == null? null : feed.getCafe().getName();
            this.content = feed.getContent();
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
            this.cafeName = feed.getCafe() == null? null : feed.getCafe().getName();
            this.content = feed.getContent();
            this.isVisible = feed.getIsVisible();
            this.grade = feed.getGrade();
            this.createdAt = feed.getCreatedAt();
            this.updatedAt = feed.getUpdatedAt();
            this.isHeart = false;
            this.heartNum = 0;
            this.image = image;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NewFeedResponseDTO {
        private ImageService imageService;
        private TagService tagService;
        private String feedId;
        private String content;
        private List<String> imageUrlList;
        private CafeDTO.CafeResponseDTO cafe;
        private Integer grade;
        private List<String> tagNameList;

        public NewFeedResponseDTO(Feed feed) {
            this.feedId = feed.getFeedId();
            this.content = feed.getContent();
            this.imageUrlList = new ArrayList<>();
            this.cafe = new CafeDTO.CafeResponseDTO(feed.getCafe());
            this.grade = feed.getGrade();
            this.tagNameList = new ArrayList<>();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindFeedDTO {
        private String feedId;
        private String nickname;
        private String name;
        private String content;
        private Integer grade;
        private List<String> imageUrls;
        private List<String> tagNames;

        public FindFeedDTO(Feed feed, Member member, Cafe cafe, List<String> imageUrls, List<String> tagNames) {
            this.feedId = feed.getFeedId();
            this.nickname = member.getNickname();
            this.name = cafe.getName();
            this.content = feed.getContent();
            this.grade = feed.getGrade();
            this.imageUrls = imageUrls;
            this.tagNames = tagNames;
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FeedWithImageResponseDTO {
        private String feedId;
        private String memberNickname;
        private String memberEmail;
        private String cafeName;
        private String content;
        private Boolean isVisible;
        private Integer grade;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<String> tagNameList;
        private List<CommentDTO.ParentCommentResponseDTO> parentCommentList;
        private List<String> imageUrls;
        private boolean isHeart;    // 사용자가 댓글을 좋아요 했는지 여부
        private int heartNum;       // 해당 댓글의 좋아요 수


        public FeedWithImageResponseDTO(Feed feed, List<String> tagNameList ,List<CommentDTO.ParentCommentResponseDTO> parentCommentList, List<String> imageUrls, boolean isHeart, int heartNum) {
            this.feedId = feed.getFeedId();
            this.memberNickname = feed.getMember().getNickname();
            this.memberEmail = feed.getMember().getEmail();
            this.cafeName = feed.getCafe().getName();
            this.content = feed.getContent();
            this.isVisible = feed.getIsVisible();
            this.grade = feed.getGrade();
            this.createdAt = feed.getCreatedAt();
            this.updatedAt = feed.getUpdatedAt();
            this.tagNameList = tagNameList;
            this.parentCommentList = parentCommentList;
            this.imageUrls = imageUrls;
            this.isHeart = isHeart;
            this.heartNum = heartNum;
        }
    }
}
