package com.grinder.domain.dto;

import com.grinder.domain.entity.Comment;
import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Report;
import com.grinder.domain.enums.ContentType;
import lombok.Getter;
import lombok.Setter;

public class ReportDTO {

    @Getter
    @Setter
    public static class FindReportDTO {
        private String reportId;
        private String memberId;
        private String contentId;
        private ContentType contentType;
        private String content;

        public FindReportDTO(Report report, Comment comment) {
            this.reportId = report.getReportId();
            this.memberId = report.getMember().getMemberId();
            this.contentId = report.getContentId();
            this.contentType = report.getContentType();
            this.content = comment.getContent().substring(0, 15);
        }

        public FindReportDTO(Report report, Feed feed) {
            this.reportId = report.getReportId();
            this.memberId = report.getMember().getMemberId();
            this.contentId = report.getContentId();
            this.contentType = report.getContentType();
            this.content = feed.getContent().substring(0, 15);
        }
    }
}
