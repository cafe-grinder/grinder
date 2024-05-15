package com.grinder.service;

import com.grinder.domain.dto.CafeRegisterDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import static com.grinder.domain.dto.CafeRegisterDTO.*;
import java.util.List;

public interface CafeRegisterService {

    Slice<FindCafeRegisterDTO> FindAllCafeRegisters(Pageable pageable);
    void deleteCafeRegister(String registerId);
    void registerCafe(CafeRegisterDTO.SaveCafeRegisterDTO cafeRegisterDTO);
}
