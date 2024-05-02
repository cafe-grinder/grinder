package com.grinder.controller.entity;

import com.grinder.domain.dto.BookmarkDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.exception.LoginRequiredException;
import com.grinder.service.BookmarkService;
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
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping("/bookmark")
    public ResponseEntity<List<BookmarkDTO.findAllResponse>> findAllBookmarksSlice(
            Authentication authentication, @PageableDefault Pageable pageable) {
        String email = Optional.ofNullable(authentication.getName()).orElseThrow(() -> new LoginRequiredException("로그인이 필요합니다."));
        List<BookmarkDTO.findAllResponse> list = bookmarkService.findAllBookmarksSlice(email, pageable);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/bookmark/{cafeId}")
    public ResponseEntity<SuccessResult> addBookmark(
            @PathVariable("cafeId")String cafeId, Authentication authentication) {
        String email = Optional.ofNullable(authentication.getName()).orElseThrow(() -> new LoginRequiredException("로그인이 필요합니다."));
        if (bookmarkService.addBookmark(email, cafeId)) return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Add Success", "추가되었습니다."));
        else throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
    }

    @DeleteMapping("/bookmark/{cafeId}")
    public ResponseEntity<SuccessResult> deleteBookmark(
            @PathVariable("cafeId")String cafeId, Authentication authentication) {
        String email = Optional.ofNullable(authentication.getName()).orElseThrow(() -> new LoginRequiredException("로그인이 필요합니다."));
        if (bookmarkService.deleteBookmark(email, cafeId)) return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Delete Success", "삭제되었습니다."));
        else throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
    }
}
