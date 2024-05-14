package com.grinder.controller.entity;

import com.grinder.domain.dto.CafeDTO;
import com.grinder.domain.dto.CafeDTO.CafeResponseDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.domain.entity.Cafe;
import com.grinder.service.CafeRegisterService;
import com.grinder.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/cafe")
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;
    private final CafeRegisterService cafeRegisterService;

    @PostMapping("/admin/{registerId}")
    public ResponseEntity<SuccessResult> addCafe(@PathVariable String registerId) {
        cafeService.saveCafe(registerId);
        cafeRegisterService.deleteCafeRegister(registerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Add new cafe", "새로운 카페정보가 등록되었습니다."));
    }

    @GetMapping("/admin")
    public ResponseEntity<Slice<CafeDTO.CafeSearchByAdminDTO>> searchCafeByAdmin(@RequestParam String keyword,@PageableDefault(size = 5) Pageable pageable) {
        Slice<CafeDTO.CafeSearchByAdminDTO> cafeSlice = cafeService.searchCafeByAdmin(keyword, pageable);
        return ResponseEntity.ok(cafeSlice);
    }

    @GetMapping("/search-cafe")
    public ResponseEntity<List<CafeResponseDTO>> searchCafes(@RequestParam String query) {
        if (query == null || query.trim().length() < 3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            List<Cafe> cafes = cafeService.findCafeList(query);
            List<CafeDTO.CafeResponseDTO> cafeDTOs = cafes.stream()
                    .map(cafe -> cafeService.getCafeInfo(cafe.getCafeId()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(cafeDTOs);
        } catch (Exception e) {
            // 예외 발생 시 로그를 출력하고 500 Internal Server Error 응답 반환
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
