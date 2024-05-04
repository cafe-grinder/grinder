package com.grinder.controller.entity;

import com.grinder.domain.dto.FollowDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.exception.LoginRequiredException;
import com.grinder.service.FollowService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FollowController {

    private final FollowService followService;

    @GetMapping("/following")
    public ResponseEntity<List<FollowDTO.findAllFollowingResponse>> findAllFollowingSlice(
            Authentication authentication,
            @PageableDefault Pageable pageable) {
        String email = Optional.ofNullable(authentication.getName()).orElseThrow(() -> new LoginRequiredException("로그인이 필요합니다."));
        List<FollowDTO.findAllFollowingResponse> list = followService.findAllFollowingSlice(email, pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/follower")
    public ResponseEntity<List<FollowDTO.findAllFollowerResponse>> findAllFollowerSlice(
            Authentication authentication,
            @PageableDefault Pageable pageable) {
        String email = Optional.ofNullable(authentication.getName()).orElseThrow(() -> new LoginRequiredException("로그인이 필요합니다."));
        List<FollowDTO.findAllFollowerResponse> list = followService.findAllFollowerSlice(email, pageable);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/follow/{email}")
    public ResponseEntity<SuccessResult> addFollow(Authentication authentication, @PathVariable("email") String followEmail) {
        String email = Optional.ofNullable(authentication.getName()).orElseThrow(() -> new LoginRequiredException("로그인이 필요합니다."));
        if (followService.addFollow(email, followEmail)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Create Success", "추가되었습니다."));
        } else throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
    }

    @DeleteMapping("/follow/{email}")
    public ResponseEntity<SuccessResult> deleteFollow(Authentication authentication, @PathVariable("email") String followEmail) {
        String email = Optional.ofNullable(authentication.getName()).orElseThrow(() -> new LoginRequiredException("로그인이 필요합니다."));
        if (followService.deleteFollow(email, followEmail)) {
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResult("Delete Success", "삭제되었습니다."));
        } else throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
    }
}
