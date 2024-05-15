package com.grinder.service;

import com.grinder.domain.dto.MenuDTO;
import com.grinder.domain.entity.Menu;
import java.util.List;

public interface MenuService {
    List<MenuDTO.findAllMenuResponse> findAllMenusByCafeId(String cafeId);
    boolean saveMyCafeMenu(MenuDTO.saveMenuRequest request);
}
