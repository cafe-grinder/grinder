package com.grinder.controller.entity;

import com.grinder.domain.dto.CommentDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.domain.entity.Comment;
import com.grinder.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment/{feed_id}")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/newcomment")
    public ResponseEntity<SuccessResult> addComment(
            Authentication authentication,
            @PathVariable String feed_id,
            @RequestBody CommentDTO.CommentRequestDTO request
    ) {
        // String memberEmail = authentication.getName();
        String memberEmail = "test@test.com";  // TODO : 테스트용. 나중에 지우기!
        Comment comment = commentService.saveComment(request, memberEmail, feed_id);

        if (comment != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Add Success", "추가되었습니다."));
        } else {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
        }
    }

    @PutMapping("/{comment_id}")
    public ResponseEntity<SuccessResult> updateComment(
            Authentication authentication,
            @PathVariable String comment_id,
            @RequestBody CommentDTO.CommentRequestDTO request
    ) {
        // String memberEmail = authentication.getName();
        String memberEmail = "test@test.com";  // TODO : 테스트용. 나중에 지우기!
        Comment comment = commentService.findComment(comment_id);
        if (memberEmail.equals(comment.getMember().getEmail())) {
            comment = commentService.updateComment(comment_id, request.getContent());
            if (comment != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Update Success", "수정되었습니다."));
            } else {
                throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
            }
        } else {    // 403에러 (회원 불일치)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity<SuccessResult> deleteComment(
            Authentication authentication,
            @PathVariable String comment_id
    ) {
        // String memberEmail = authentication.getName();
        String memberEmail = "test@test.com";  // TODO : 테스트용. 나중에 지우기!
        Comment comment = commentService.findComment(comment_id);
        if (memberEmail.equals(comment.getMember().getEmail())) {
            commentService.deleteComment(comment_id);
            if (!comment.getIsVisible()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Delete Success", "삭제되었습니다."));
            } else {
                throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
            }
        } else {    // 403에러 (회원 불일치)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/admin/{comment_id}")
    public ResponseEntity<CommentDTO.FindCommentDTO> findCommentForAdmin(@PathVariable String comment_id, Authentication authentication) {
        if(!authentication.getAuthorities().contains("ADMIN")) {
            throw new PermissionDeniedDataAccessException("관리자 권한이 필요합니다", null);
        }
        CommentDTO.FindCommentDTO commentDTO = commentService.findCommentForAdmin(comment_id);

        return ResponseEntity.ok(commentDTO);
    }
}
