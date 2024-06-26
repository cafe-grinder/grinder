package com.grinder.domain.dto;

import com.grinder.domain.entity.Comment;
import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Member;
import com.grinder.domain.entity.Report;
import com.grinder.domain.enums.ContentType;
import lombok.Getter;
import lombok.Setter;

public class ReportDTO {

    @Getter
    @Setter
    public static class FindReportDTO {
        private String reportId;
        private String nickname;
        private String contentId;
        private ContentType contentType;
        private String content;

        public FindReportDTO(Report report, Comment comment) {
            this.reportId = report.getReportId();
            this.nickname = report.getMember().getNickname();
            this.contentId = report.getContentId();
            this.contentType = report.getContentType();
            if (comment.getContent().length() <= 65) {
                this.content = comment.getContent();
            } else {
                this.content = comment.getContent().substring(0, 65) + "...";
            }
        }

        public FindReportDTO(Report report, Feed feed) {
            this.reportId = report.getReportId();
            this.nickname = report.getMember().getNickname();
            this.contentId = report.getContentId();
            this.contentType = report.getContentType();
            if (feed.getContent().length() <= 65) {
                this.content = feed.getContent();
            } else {
                this.content = feed.getContent().substring(0, 65) + "...";
            }
        }
    }
}
