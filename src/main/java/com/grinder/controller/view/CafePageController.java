package com.grinder.controller.view;

import com.grinder.domain.dto.CafeDTO.CafeResponseDTO;
import com.grinder.domain.dto.CafeSummaryDTO;
import com.grinder.domain.dto.CafeSummaryDTO.CafeSummaryResponse;
import com.grinder.domain.dto.FeedDTO;
import com.grinder.domain.dto.MenuDTO;
import com.grinder.service.CafeService;

import com.grinder.service.CafeSummaryService;
import com.grinder.service.FeedService;
import com.grinder.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cafe")
@RequiredArgsConstructor
public class CafePageController {
    private final CafeService cafeService;
    private final MenuService menuService;
    private final CafeSummaryService cafeSummaryService;

    @GetMapping("/newcafe")
    public String getAddCafeForm(){
        return "addCafeForm";
    }

    @GetMapping("/{cafeId}")
    public String getCafeInfo(Model model, @PathVariable("cafeId") String cafeId) {
        CafeResponseDTO cafeInfo = cafeService.getCafeInfo(cafeId);
        model.addAttribute("cafeInfo", cafeInfo);
        return "cafeInfo";
    }

    @GetMapping("/seller_apply/{cafeId}") String applyCafeSeller(@PathVariable String cafeId, Model model) {
        model.addAttribute("cafeId", cafeId);
        return "sellerApplicationForm";
    }

    @GetMapping("/add")
    public String addCafeInfo(){
        return "addCafeForm";
    }

    @GetMapping("/{cafeId}/menu")
    public String getCafeMenu(Model model, @PathVariable("cafeId") String cafeId) {
        List<MenuDTO.findAllMenuResponse> menuList = menuService.findAllMenusByCafeId(cafeId);
        model.addAttribute("menuList", menuList);
        return "components/menuCard :: cafeInfoMenuCard";
    }

    @GetMapping("/{cafeId}/cafe_summary")
    public String findCafeSummary(Model model, @PathVariable("cafeId")String cafeId) {
        CafeSummaryDTO.CafeSummaryResponse response = cafeSummaryService.findCafeSummary(cafeId);
        model.addAttribute("summary", response);
        return "components/cafeSummary :: cafeSummary";
    }
}
