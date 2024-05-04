package com.grinder.controller.entity;

import com.grinder.domain.dto.HeartDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.domain.entity.Heart;
import com.grinder.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/heart")
public class HeartController {
    private final HeartService heartService;

    @PostMapping
    public ResponseEntity<SuccessResult> addHeart(
            Authentication authentication,
            @RequestBody HeartDTO.HeartRequestDTO request
    ) {
        String memberEmail = authentication.getName();
        Heart heart = heartService.addHeart(memberEmail, request);

        if (heart != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Add Success", "추천했습니다."));
        } else {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
        }
    }

    @DeleteMapping
    public ResponseEntity<SuccessResult> deleteHeart(
            Authentication authentication,
            @RequestBody HeartDTO.HeartRequestDTO request
    ) {
        String memberEmail = authentication.getName();
        Heart heart = heartService.findHeart(memberEmail, request);
        if (memberEmail.equals(heart.getMember().getEmail())) {
            heartService.deleteHeart(memberEmail, request);
            heart = heartService.findHeart(memberEmail, request);
            if (heart == null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Delete Success", "추천 해제했습니다."));
            } else {
                throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
            }
        } else {    // 403에러 (회원 불일치)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
