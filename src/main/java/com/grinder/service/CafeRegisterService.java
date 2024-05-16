package com.grinder.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import static com.grinder.domain.dto.CafeRegisterDTO.*;

public interface CafeRegisterService {

    Slice<FindCafeRegisterDTO> FindAllCafeRegisters(Pageable pageable);
    void deleteCafeRegister(String registerId);
    String saveCafeRegister(String memberEmail, CafeRegisterRequestDTO cafeRegisterDTO);

}
