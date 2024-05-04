package com.grinder.controller;

import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.SellerApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.grinder.domain.dto.SellerApplyDTO.*;

@Controller
@RequestMapping("/api/seller_apply")
@RequiredArgsConstructor
public class SellerApplyController {

    private final SellerApplyService sellerApplyService;

    @GetMapping("/find")
    public void findAllSellerApplies(Model model) {
        List<FindSellerApplyDTO> sellerApplyList = sellerApplyService.findAllSellerApplies();
        model.addAttribute("sellerApplyList", sellerApplyList);
    }

    @DeleteMapping("/delete/{applyId}")
    @ResponseBody
    public ResponseEntity<SuccessResult> deleteSellerApply(@PathVariable String applyId) {
        sellerApplyService.deleteSellerApply(applyId);
        return ResponseEntity.ok(new SuccessResult("Success", "요청이 성공적으로 처리되었습니다."));
    }
}
