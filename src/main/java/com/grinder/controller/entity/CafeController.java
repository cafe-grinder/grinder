package com.grinder.controller.entity;

import com.grinder.domain.dto.CafeDTO;
import com.grinder.domain.dto.CafeDTO.CafeResponseDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.domain.entity.Cafe;
import com.grinder.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cafe")
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @GetMapping("/{cafeId}")
    public ResponseEntity<CafeResponseDTO> getCafeInfo(@PathVariable String cafeId) {
        Cafe cafe = cafeService.findCafeById(cafeId);
        CafeResponseDTO cafeInfo = CafeResponseDTO.builder()
            .cafeId(cafe.getCafeId())
            .name(cafe.getName())
            .address(cafe.getAddress())
            .phoneNum(cafe.getPhoneNum())
            .averageGrade(cafe.getAverageGrade())
            .build();
        return ResponseEntity.ok(cafeInfo);
    }

    @PostMapping("/{registerId}")
    public ResponseEntity<SuccessResult> addCafe(@PathVariable String registerId) {
        cafeService.saveCafe(registerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Add new cafe", "새로운 카페정보가 등록되었습니다."));
    }

}
