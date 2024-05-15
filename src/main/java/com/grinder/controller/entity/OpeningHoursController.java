package com.grinder.controller.entity;

import com.grinder.domain.dto.OpeningHoursDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.OpeningHoursService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OpeningHoursController {
    private final OpeningHoursService openingHoursService;

    @PostMapping("/cafe/newcafe/openinghours")
    public String saveNewOpeningHours(@RequestBody OpeningHoursDTO openingHoursDTO) {

        return "";
    }

    @PostMapping("/cafe/newcafe/openinghours")
    public ResponseEntity<String> saveOpeningHours(@RequestBody List<OpeningHoursDTO.saveOpeningRequest> openingHours) {
        // Assuming you have a way to get cafeId
        String cafeId = "yourCafeId";
        try {
            openingHoursService.saveOpeningHours(cafeId, openingHours);
            return ResponseEntity.ok("Opening hours saved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save opening hours: " + e.getMessage());
        }
    }

    @PostMapping("/api/saveOpeningHours/{cafe_id}")
    public ResponseEntity<SuccessResult> saveOpeningHours(@PathVariable("cafe_id")String cafeId
            , @RequestBody List<OpeningHoursDTO.saveOpeningRequest> list) {
        if (!openingHoursService.saveOpeningHours(cafeId, list)) {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
        } else return ResponseEntity.ok(new SuccessResult("성공", "저장이 성공하였습니다."));
    }
}
