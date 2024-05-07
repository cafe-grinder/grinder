package com.grinder.controller;

import com.grinder.domain.dto.SuccessResult;
import com.grinder.security.CustomUserDetails;
import com.grinder.service.SellerApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import static com.grinder.domain.dto.SellerApplyDTO.*;

@Controller
@RequestMapping("/api/seller_apply")
@RequiredArgsConstructor
public class SellerApplyController {

    private final SellerApplyService sellerApplyService;

    @GetMapping
    public void findAllSellerApplies(Model model) {
        List<FindSellerApplyDTO> sellerApplyList = sellerApplyService.findAllSellerApplies();
        model.addAttribute("sellerApplyList", sellerApplyList);
    }

    @DeleteMapping("/{applyId}")
    @ResponseBody
    public ResponseEntity<SuccessResult> deleteSellerApply(@PathVariable String applyId) {
        sellerApplyService.deleteSellerApply(applyId);
        return ResponseEntity.ok(new SuccessResult("Delete seller_apply", "판매자 신청이 삭제되었습니다."));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<SuccessResult> saveSellerApply(@RequestParam String cafeId, @RequestPart(value = "file")MultipartFile file, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String memberId = userDetails.getMemberId();

        sellerApplyService.saveSellerApply(memberId, cafeId, file);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("Create seller apply", "판매자 신청이 완료되었습니다."));
    }
}
