package com.grinder.service.implement;

import com.grinder.domain.entity.Comment;
import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Member;
import com.grinder.repository.CommentRepository;
import com.grinder.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment findComment(String commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public List<Comment> findParentCommentList(String feedId) {
        return commentRepository.findByFeed_FeedIdAndParentCommentIsNullAndIsVisibleTrue(feedId);
    }

    @Override
    public List<Comment> findChildrenCommentList(String parentId) {
        return commentRepository.findByParentComment_CommentIdAndIsVisibleTrue(parentId);
    }

    @Override
    public Comment saveComment(String content, Member member, Feed feed, Comment parentComment) {
        return commentRepository.save(
                Comment.builder()
                        .content(content)
                        .member(member)
                        .feed(feed)
                        .parentComment(parentComment)
                        .build()
        );
    }

    @Override
    public Comment updateComment(String commentId, String content) {
        Comment comment = findComment(commentId);
        comment.updateContent(content);
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(String commentId) {
        commentRepository.deleteById(commentId);
    }
}
