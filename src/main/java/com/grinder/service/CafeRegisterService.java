package com.grinder.service;

import static com.grinder.domain.dto.CafeRegisterDTO.*;
import java.util.List;

public interface CafeRegisterService {

    List<FindCafeRegisterDTO> FindAllCafeRegisters();
    void deleteCafeRegister(String registerId);
}
