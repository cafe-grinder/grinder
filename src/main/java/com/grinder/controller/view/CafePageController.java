package com.grinder.controller.view;

import com.grinder.domain.dto.CafeDTO.CafeResponseDTO;
import com.grinder.service.CafeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cafe")
@RequiredArgsConstructor
public class CafePageController {
    private final CafeService cafeService;

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

}
