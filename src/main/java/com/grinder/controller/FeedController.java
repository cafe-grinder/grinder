package com.grinder.controller;

import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Member;
import com.grinder.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feedService;

    @PostMapping("/newfeed")
    public ResponseEntity<FeedDTO.FeedResponseDTO> newFeed(
            @AuthenticationPrincipal Member member,
            FeedDTO.FeedRequestDTO request
    ) {
        Feed feed = feedService.saveFeed(request, member);
        FeedDTO.FeedResponseDTO response = new FeedDTO.FeedResponseDTO(feed);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{feed_id}")
    public ResponseEntity<FeedDTO.FeedResponseDTO> updateFeed(
            @AuthenticationPrincipal Member member,
            @PathVariable String feed_id,
            FeedDTO.FeedRequestDTO request
    ) {
        Feed feed = feedService.updateFeed(feed_id, request);
        if (member.equals(feed.getMember())) {
            FeedDTO.FeedResponseDTO response = new FeedDTO.FeedResponseDTO(feed);
            return ResponseEntity.ok(response);
        } else {    // 403에러 (회원 불일치)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/{feed_id}")
    public ResponseEntity<Void> deleteFeed(
            @AuthenticationPrincipal Member member,
            @PathVariable String feed_id
    ) {
        Feed feed = feedService.findFeed(feed_id);
        if (member.equals(feed.getMember())) {
            feedService.deleteFeed(feed_id);
            return ResponseEntity.ok().build();
        } else {    // 403에러 (회원 불일치)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
