package com.grinder.service;

import com.grinder.domain.entity.Comment;
import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Member;

import java.util.List;

public interface CommentService {
    List<Comment> findParentCommentList(String feedId);
    Comment findComment(String commentId);
    List<Comment> findChildrenCommentList(String parentId);
    Comment saveComment(String content, Member member, Feed feed, Comment parentComment);
    Comment updateComment(String commentId, String content);
    void deleteById(String commentId);
}
