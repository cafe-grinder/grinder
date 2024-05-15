package com.grinder.controller.entity;

import com.grinder.domain.dto.MenuDTO;
import com.grinder.domain.dto.SuccessResult;
import com.grinder.domain.entity.Menu;
import com.grinder.service.MenuService;
import java.util.List;
import java.util.Optional;

import com.grinder.service.MyMenuService;
import com.grinder.service.SellerInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final MyMenuService myMenuService;
    private final SellerInfoService sellerInfoService;


    @DeleteMapping("/myMenu/{menu_id}")
    @ResponseBody
    public ResponseEntity<SuccessResult> deleteMyCafeMenu(@PathVariable("menu_id")String menuId, @RequestParam("cafeId") String cafeId) {
        if (!myMenuService.deleteMenu(menuId, cafeId)) {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
        } else {
            return ResponseEntity.ok().body(new SuccessResult("성공", "삭제되었습니다."));
        }
    }

    @PostMapping("/menu")
    @ResponseBody
    public ResponseEntity<SuccessResult> saveMyCafeMenu(@ModelAttribute MenuDTO.saveMenuRequest request) {
        String email = getEmail();
        if (!sellerInfoService.existByMemberAndCafe(request.getCafeId(), email)) throw new IllegalArgumentException("관리자 외 수정할 수 없습니다.");
        if (!menuService.saveMyCafeMenu(request)) throw new IllegalArgumentException("잘못된 요청입니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResult("성공", "저장되었습니다."));
    }


    private String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  Optional.ofNullable(authentication.getName()).orElse(null);
    }
}
