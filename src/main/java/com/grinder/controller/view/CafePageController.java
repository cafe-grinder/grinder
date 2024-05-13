package com.grinder.controller.view;

import com.grinder.domain.dto.CafeDTO.CafeResponseDTO;
import com.grinder.domain.dto.FeedDTO.FeedResponseDTO;
import com.grinder.service.CafeService;
import com.grinder.service.FeedService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class CafePageController {
    private final CafeService cafeService;
    private final FeedService feedService;

    @GetMapping("/cafe/{cafeId}")
    public String getCafeInfo(Model model, @PathVariable("cafeId") String cafeId) {
        CafeResponseDTO cafeInfo = cafeService.getCafeInfo(cafeId);
        model.addAttribute("cafeInfo", cafeInfo);
        return "cafeInfo";
    }

    @GetMapping("/{cafeId}/feed")
    public String getCafeFeed(Model model, @PathVariable String cafeId) {
        List<FeedResponseDTO> feedList = feedService.findFeedsByCafeId(cafeId);
        //TODO: 받아온 feedList로 view에 피드 뿌려주는 부분 작성 필요

        return "";
    }

    @GetMapping("/cafe/seller_apply/{cafeId}") String applyCafeSeller(@PathVariable String cafeId, Model model) {
        model.addAttribute("cafeId", cafeId);
        return "sellerApplicationForm";
    }

}
