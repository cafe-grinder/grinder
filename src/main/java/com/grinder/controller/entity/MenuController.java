package com.grinder.controller.entity;

import com.grinder.domain.entity.Menu;
import com.grinder.service.MenuService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/cafe/{cafeId}/menu")
@RequiredArgsConstructor
public class MenuController {
    public MenuService menuService;

    @GetMapping("")
    public ResponseEntity<List<Menu>> findAllMenusByCafeId(@PathVariable String cafeId) {
        List<Menu> menus = menuService.findAllMenusByCafeId(cafeId);
        if (menus.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }
}
