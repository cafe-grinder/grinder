package com.grinder.service;

import com.grinder.domain.entity.Cafe;

import static com.grinder.domain.dto.CafeRegisterDTO.*;

public interface CafeService {

    void saveCafe(String registerId);

    Cafe findCafeById(String cafeId);
}
