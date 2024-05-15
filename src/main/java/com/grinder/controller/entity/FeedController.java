package com.grinder.controller.entity;

import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.domain.entity.Feed;
import com.grinder.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feedService;

    @PostMapping("/newfeed")
    public ResponseEntity<SuccessResult> addFeed(
            Authentication authentication,
            @ModelAttribute FeedDTO.FeedRequestDTO request,
            @RequestPart(value = "imageList", required = false) List<MultipartFile> imageList
    ) {
        // String memberEmail = authentication.getName();
        String memberEmail = "test@test.com";  // TODO : 테스트용. 나중에 지우기!
        Feed feed = feedService.saveFeed(request, memberEmail, imageList);

        if (feed != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Add Success", "추가되었습니다."));
        } else {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
        }
    }

    @PutMapping("/{feed_id}")
    public ResponseEntity<SuccessResult> updateFeed(
            Authentication authentication,
            @PathVariable String feed_id,
            @ModelAttribute FeedDTO.FeedRequestDTO request,
            @RequestPart(value = "imageList", required = false) List<MultipartFile> imageList
    ) {
        // String memberEmail = authentication.getName();
        String memberEmail = "test@test.com";  // TODO : 테스트용. 나중에 지우기!
        Feed feed = feedService.findFeed(feed_id);
        if (memberEmail.equals(feed.getMember().getEmail())) {
            feed = feedService.updateFeed(feed_id, request, imageList);
            if (feed != null) {
                return ResponseEntity.ok(new SuccessResult("Update Success", "수정되었습니다."));
            } else {
                throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
            }
        } else {    // 403에러 (회원 불일치)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/{feed_id}")
    public ResponseEntity<SuccessResult> deleteFeed(
            Authentication authentication,
            @PathVariable String feed_id
    ) {
        // String memberEmail = authentication.getName();
        String memberEmail = "test@test.com";  // TODO : 테스트용. 나중에 지우기!
        Feed feed = feedService.findFeed(feed_id);
        if (memberEmail.equals(feed.getMember().getEmail())) {
            feedService.deleteFeed(feed_id);
            if (!feed.getIsVisible()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Delete Success", "삭제되었습니다."));
            } else {
                throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
            }
        } else {    // 403에러 (회원 불일치)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
