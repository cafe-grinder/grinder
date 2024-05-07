package com.grinder.repository.queries;

import com.grinder.domain.dto.CommentDTO;
import com.grinder.domain.entity.QComment;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CommentQueryRepository {

    private final JPAQueryFactory queryFactory;

    public CommentQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Optional<CommentDTO.FindCommentDTO> findComment(String commentId) {
        QComment comment = QComment.comment;

        CommentDTO.FindCommentDTO commentDTO = queryFactory
                .select(Projections.constructor
                        (CommentDTO.FindCommentDTO.class,
                        comment,
                        comment.member))
                .from(comment)
                .where(comment.commentId.eq(commentId))
                .fetchOne();

        return Optional.ofNullable(commentDTO);
    }
}
