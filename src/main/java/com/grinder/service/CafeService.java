package com.grinder.service;

import com.grinder.domain.dto.CafeDTO;
import com.grinder.domain.entity.Cafe;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

import static com.grinder.domain.dto.CafeRegisterDTO.*;

public interface CafeService {

    void saveCafe(String registerId);

    Cafe findCafeById(String cafeId);

    Slice<CafeDTO.CafeSearchByAdminDTO> searchCafeByAdmin(String keyword, Pageable pageable);

    List<Cafe> findCafeList(String cafeName);
}
