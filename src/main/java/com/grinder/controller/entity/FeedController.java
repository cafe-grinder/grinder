package com.grinder.controller.entity;

import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Member;
import com.grinder.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {
    private final FeedService feedService;

    @PostMapping("/newfeed")
    public ResponseEntity<FeedDTO.FeedResponseDTO> addFeed(
            Authentication authentication,
            @RequestBody FeedDTO.FeedRequestDTO request,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        String memberEmail = authentication.getName();

        Feed feed = feedService.saveFeed(request, memberEmail, file);
        FeedDTO.FeedResponseDTO response = new FeedDTO.FeedResponseDTO(feed);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{feed_id}")
    public ResponseEntity<FeedDTO.FeedResponseDTO> updateFeed(
            Authentication authentication,
            @PathVariable String feed_id,
            @RequestBody FeedDTO.FeedRequestDTO request
    ) {
        String memberEmail = authentication.getName();
        Feed feed = feedService.updateFeed(feed_id, request);
        if (memberEmail.equals(feed.getMember().getEmail())) {
            FeedDTO.FeedResponseDTO response = new FeedDTO.FeedResponseDTO(feed);
            return ResponseEntity.ok(response);
        } else {    // 403에러 (회원 불일치)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/{feed_id}")
    public ResponseEntity<Void> deleteFeed(
            Authentication authentication,
            @PathVariable String feed_id
    ) {
        String memberEmail = authentication.getName();
        Feed feed = feedService.findFeed(feed_id);
        if (memberEmail.equals(feed.getMember().getEmail())) {
            feedService.deleteFeed(feed_id);
            return ResponseEntity.ok().build();
        } else {    // 403에러 (회원 불일치)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
