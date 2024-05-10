package com.grinder.service;

import com.grinder.domain.dto.OpeningHoursDTO;

import java.util.List;

public interface OpeningHoursService {
    boolean saveOpeningHours(String cafeId, List<OpeningHoursDTO.saveOpeningRequest> list);
    public boolean updateOpeningHours(String cafeId, List<OpeningHoursDTO.saveOpeningRequest> list);
}
