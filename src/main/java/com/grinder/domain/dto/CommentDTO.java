package com.grinder.domain.dto;

import com.grinder.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CommentDTO {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentRequestDTO {
        private String content;
        private String parentCommentId;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentResponseDTO {
        private String commentId;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String nickname;
        private String memberId;
        private String feedId;
        private String parentCommentId;
        private boolean isHeart;    // 사용자가 댓글을 좋아요 했는지 여부
        private int heartNum;       // 해당 댓글의 좋아요 수

        public CommentResponseDTO(Comment comment) {
            this.commentId = comment.getCommentId();
            this.content = comment.getContent();
            this.createdAt = comment.getCreatedAt();
            this.updatedAt = comment.getUpdatedAt();
            this.nickname = comment.getMember().getNickname();
            this.memberId = comment.getMember().getMemberId();
            this.feedId = comment.getFeed().getFeedId();
            this.parentCommentId = comment.getParentComment().getCommentId();
            this.isHeart = false;
            this.heartNum = 0;
        }
    }
}
