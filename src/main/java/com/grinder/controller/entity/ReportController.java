package com.grinder.controller.entity;

import com.grinder.domain.dto.CommentDTO;
import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.CommentService;
import com.grinder.service.FeedService;
import com.grinder.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import static com.grinder.domain.dto.ReportDTO.*;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/{content_id}")
    public ResponseEntity<SuccessResult> addReport(@PathVariable("content_id")String contentId) {
        String email = getEmail();
        if (reportService.addReport(contentId,email)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("성공", "신고 완료"));
        } else throw new IllegalArgumentException("예상치 못한 예외 발생");
    }


    private String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  Optional.ofNullable(authentication.getName()).orElse(null);
    }
}
