package com.grinder.controller.entity;

import com.grinder.domain.dto.SuccessResult;
import com.grinder.domain.entity.Menu;
import com.grinder.service.MenuService;
import java.util.List;

import com.grinder.service.MyMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;
    private final MyMenuService myMenuService;

    @GetMapping("/cafe/{cafeId}/menu")
    public ResponseEntity<List<Menu>> findAllMenusByCafeId(@PathVariable String cafeId) {
        List<Menu> menus = menuService.findAllMenusByCafeId(cafeId);
        if (menus.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @DeleteMapping("/myMenu/{menu_id}")
    @ResponseBody
    public ResponseEntity<SuccessResult> deleteMyCafeMenu(@PathVariable("menu_id")String menuId, @RequestParam("cafeId") String cafeId) {
        if (!myMenuService.deleteMenu(menuId, cafeId)) {
            throw new IllegalArgumentException("예상치 못한 에러가 발생했습니다.");
        } else {
            return ResponseEntity.ok().body(new SuccessResult("성공", "삭제되었습니다."));
        }
    }
}
