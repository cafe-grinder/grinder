package com.grinder.controller.entity;

import com.grinder.domain.dto.ImageDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.ImageService;
import com.grinder.service.MemberService;
import com.grinder.service.SellerInfoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageController {
    private final ImageService imageService;
    private final SellerInfoService sellerInfoService;
    private final MemberService memberService;

    @PostMapping("/image")
    public ResponseEntity<SuccessResult> saveAndDeleteImage(@ModelAttribute ImageDTO.UpdateRequest request) {
        String email = getEmail();
        if (request.getCafeId() != null) {
            if(!sellerInfoService.existByMemberAndCafe(request.getCafeId(), email)) {
                throw new EntityNotFoundException("관리자가 아닙니다.");
            } else imageService.saveProfile(request, email);
        }  else {
            if (!memberService.existEmail(email)) {
                throw new IllegalArgumentException("본인만 변경할 수 있습니다.");
            } else imageService.saveProfile(request, email);
        }
        return ResponseEntity.ok(new SuccessResult("성공", "저장되었습니다."));
    }

    @DeleteMapping("/image/{cafeId}")
    public ResponseEntity<SuccessResult> deleteCafeImage(@PathVariable("cafeId")String cafeId) {
        String email = getEmail();
        if(!sellerInfoService.existByMemberAndCafe(cafeId, email)) {
            throw new EntityNotFoundException("관리자가 아닙니다.");
        }else {
            imageService.deleteCafeProfile(cafeId);
        }
        return ResponseEntity.ok(new SuccessResult("성공", "삭제되었습니다."));
    }

    @DeleteMapping("/image")
    public ResponseEntity<SuccessResult> deleteImage() {
        String email = getEmail();
        if (!memberService.existEmail(email)) {
            throw new IllegalArgumentException("본인만 변경할 수 있습니다.");
        }
        imageService.deleteProfile(email);
        return ResponseEntity.ok(new SuccessResult("성공", "삭제되었습니다."));
    }

    private String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  Optional.ofNullable(authentication.getName()).orElse(null);
    }
}
