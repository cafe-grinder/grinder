package com.grinder.repository;

import com.grinder.domain.entity.Comment;
import com.grinder.domain.entity.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findByFeed_FeedIdAndParentCommentIsNullAndIsVisibleTrue(String feedId);
    List<Comment> findByParentComment_CommentIdAndIsVisibleTrue(String parentId);
    Long countByFeed(Feed feed);
}
