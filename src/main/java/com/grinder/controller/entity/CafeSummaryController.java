package com.grinder.controller.entity;

import com.grinder.domain.dto.CafeSummaryDTO;
import com.grinder.service.CafeSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CafeSummaryController {
    private final CafeSummaryService cafeSummaryService;

    @GetMapping("/cafe_summary/{cafeId}")
    public ResponseEntity<CafeSummaryDTO.CafeSummaryResponse> findCafeSummary(@PathVariable("cafeId")String cafeId) {
        CafeSummaryDTO.CafeSummaryResponse response = cafeSummaryService.findCafeSummary(cafeId);
        return ResponseEntity.ok().body(response);
    }
}
