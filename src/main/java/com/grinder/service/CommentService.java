package com.grinder.service;

import com.grinder.domain.dto.CommentDTO;
import com.grinder.domain.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findParentCommentList(String feedId);
    Comment findComment(String commentId);
    List<Comment> findChildrenCommentList(String parentId);
    Comment saveComment(CommentDTO.CommentRequestDTO request, String memberEmail, String feed_id);
    Comment updateComment(String commentId, String content);
    void deleteComment(String commentId);
}
