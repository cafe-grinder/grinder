package com.grinder.service;

import com.grinder.domain.dto.MenuDTO;

import java.util.List;

public interface MyMenuService {

    List<MenuDTO.findAllMenuResponse> findAllMenuWithImage(String email, String cafeId);
    boolean deleteMenu(String cafeId, String menuId);
}
