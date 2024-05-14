package com.grinder.controller.entity;

import com.grinder.domain.dto.SuccessResult;
import com.grinder.domain.entity.AnalysisTag;
import com.grinder.repository.AnalysisTagRepository;
import com.grinder.service.AnalysisTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AnalysisTagController {
    private final AnalysisTagService analysisTagService;

    @PostMapping("/saveAlanTag")
    public ResponseEntity<SuccessResult> saveAlanTag(@RequestBody List<String> tags) {
        String email = getEmail();
        AnalysisTag analysisTag = analysisTagService.findByEmail(email);
        if (analysisTagService.addTagList(tags, analysisTag)) {
            return ResponseEntity.ok(new SuccessResult("성공", "반영되었습니다."));
        } else throw new IllegalArgumentException("예상치 못한 문제가 발생했습니다.");
    }

    private String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  Optional.ofNullable(authentication.getName()).orElse(null);
    }
}
