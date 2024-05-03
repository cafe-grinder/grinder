package com.grinder.service;

import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.Feed;
import com.grinder.domain.entity.Menu;

public interface CafeService {
    //cafeId로 Cafe 찾기
    public Cafe findCafe(String cafeId);
}
