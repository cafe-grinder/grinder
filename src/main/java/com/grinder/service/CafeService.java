package com.grinder.service;

import com.grinder.domain.dto.CafeDTO;
import com.grinder.domain.dto.CafeDTO.CafeResponseDTO;
import com.grinder.domain.entity.Cafe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CafeService {

    void saveCafe(String registerId);

    Cafe findCafeById(String cafeId);

    Slice<CafeDTO.CafeSearchByAdminDTO> searchCafeByAdmin(String keyword, Pageable pageable);

    CafeResponseDTO getCafeInfo(String cafeId);
}
