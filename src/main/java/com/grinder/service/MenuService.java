package com.grinder.service;

import com.grinder.domain.entity.Menu;

public interface MenuService {
    //cafeId로 Menu 찾기
    public Menu findMenuByCafeId(String cafeId);
}
