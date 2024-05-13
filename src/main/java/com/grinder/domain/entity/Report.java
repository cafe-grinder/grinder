package com.grinder.domain.entity;

import com.grinder.domain.enums.ContentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "report", indexes = {
        @Index(name = "idx_content_type", columnList = "content_type")
})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    @Column(name = "report_id", updatable = false, length = 36)
    private String reportId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "content_id",nullable = false, length = 36)
    private String contentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false, length = 16)
    private ContentType contentType;

    @PrePersist
    public void prePersist() {
        reportId = reportId == null ? UUID.randomUUID().toString() : reportId;
    }
}
