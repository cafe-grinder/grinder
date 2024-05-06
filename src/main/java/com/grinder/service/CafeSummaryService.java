package com.grinder.service;

import com.grinder.domain.dto.AlanDTO;
import com.grinder.domain.dto.CafeSummaryDTO;
import com.grinder.domain.entity.Cafe;

public interface CafeSummaryService {
    AlanDTO.AlanResponse AnalysisCafe(String cafeId);
    boolean updateCafeSummary(String cafeId);
    boolean deleteCafeSummary(String cafeId);
    CafeSummaryDTO.CafeSummaryResponse findCafeSummary(String cafeId);
}
