package com.grinder.controller.entity;

import com.grinder.domain.dto.CafeDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cafe")
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @PostMapping("/{registerId}")
    public ResponseEntity<SuccessResult> addCafe(@PathVariable String registerId) {
        cafeService.saveCafe(registerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Add new cafe", "새로운 카페정보가 등록되었습니다."));
    }

    @GetMapping("/admin")
    public ResponseEntity<Slice<CafeDTO.CafeSearchByAdminDTO>> searchCafeByAdmin(@RequestParam String keyword,@PageableDefault(size = 5) Pageable pageable) {
        Slice<CafeDTO.CafeSearchByAdminDTO> cafeSlice = cafeService.searchCafeByAdmin(keyword, pageable);
        return ResponseEntity.ok(cafeSlice);
    }
}
