package com.grinder.controller.view;

import com.grinder.domain.dto.CafeDTO.CafeResponseDTO;
import com.grinder.domain.dto.CafeRegisterDTO;
import com.grinder.domain.dto.FeedDTO.FeedResponseDTO;
import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.Menu;
import com.grinder.service.CafeRegisterService;
import com.grinder.service.CafeService;
import com.grinder.service.FeedService;
import com.grinder.service.MenuService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cafe")
@RequiredArgsConstructor
public class CafePageController {
    private final CafeService cafeService;
    private final MenuService menuService;
    private final FeedService feedService;
    private final CafeRegisterService cafeRegisterService;

    @GetMapping("/newcafe")
    public String getAddCafeForm(){
        return "addCafeForm";
    }

    @PostMapping("/newcafe")
    public ResponseEntity<String> saveAddCafeForm(@RequestBody CafeRegisterDTO.SaveCafeRegisterDTO cafeRegisterDTO) {
        try {
            cafeRegisterService.registerCafe(cafeRegisterDTO);
            return ResponseEntity.ok("Cafe registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register cafe: " + e.getMessage());
        }
    }

    @GetMapping("/{cafeId}")
    public String getCafeInfo(Model model, @PathVariable("cafeId") String cafeId) {
        CafeResponseDTO cafeInfo = cafeService.getCafeInfo(cafeId);
        model.addAttribute("cafeInfo", cafeInfo);
        return "cafeInfo";
    }

    @GetMapping("/{cafeId}/menu")
    public String getCafeMenu(Model model, @PathVariable("cafeId") String cafeId) {
        CafeResponseDTO cafeInfo = cafeService.getCafeInfo(cafeId);
        List<Menu> menuList = menuService.findAllMenusByCafeId(cafeId);
        model.addAttribute("cafeInfo", cafeInfo);
        model.addAttribute("menuList", menuList);
        return "components/menuCard";
    }

    @GetMapping("/seller_apply/{cafeId}") String applyCafeSeller(@PathVariable("cafeId") String cafeId, Model model) {
        model.addAttribute("cafeId", cafeId);
        return "sellerApplicationForm";
    }

}
