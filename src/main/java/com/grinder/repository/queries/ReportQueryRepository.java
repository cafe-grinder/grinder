package com.grinder.repository.queries;

import com.grinder.domain.entity.QComment;
import com.grinder.domain.entity.QFeed;
import com.grinder.domain.entity.QReport;
import com.grinder.domain.entity.Report;
import com.grinder.domain.enums.ContentType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class ReportQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ReportQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Slice<Report> searchReport(String keyword, String contentType, Pageable pageable) {
        QReport report = QReport.report;
        QFeed feed = QFeed.feed;
        QComment comment = QComment.comment;

        BooleanExpression keywordExpression = null;
        if (keyword != null && keyword != "") {
            keywordExpression = report.contentId.in(
                    JPAExpressions
                            .select(comment.commentId)
                            .from(comment)
                            .where(comment.content.contains(keyword))
                    )
                    .or(report.contentId.in(
                                    JPAExpressions
                                            .select(feed.feedId)
                                            .from(feed)
                                            .where(feed.content.contains(keyword))
                            )
                    );
        }

        BooleanExpression typeExpression = null;
        if (contentType != "") {
            typeExpression = report.contentType.eq(ContentType.valueOf(contentType));
        }

        List<Report> reportList = queryFactory
                .selectFrom(report)
                .where(keywordExpression, typeExpression)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = reportList.size() > pageable.getPageSize();
        if (hasNext) {
            reportList.remove(reportList.size() - 1);
        }
        return new SliceImpl<>(reportList, pageable, hasNext);
    }
}

