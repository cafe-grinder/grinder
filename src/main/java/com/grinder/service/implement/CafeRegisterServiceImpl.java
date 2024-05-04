package com.grinder.service.implement;

import com.grinder.domain.entity.CafeRegister;
import com.grinder.repository.CafeRegisterRepository;
import com.grinder.repository.CafeRepository;
import com.grinder.service.CafeRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static com.grinder.domain.dto.CafeRegisterDTO.*;

@Service
@RequiredArgsConstructor
public class CafeRegisterServiceImpl implements CafeRegisterService {

    private final CafeRegisterRepository cafeRegisterRepository;

    private final CafeRepository cafeRepository;

    @Override
    public List<FindCafeRegisterDTO> FindAllCafeRegisters() {
        List<CafeRegister> registerList = cafeRegisterRepository.findAll();

        List<FindCafeRegisterDTO> registerDTOList = registerList.stream().map(register -> new FindCafeRegisterDTO(register)).toList();

        return registerDTOList;
    }

    @Override
    @Transactional
    public void deleteCafeRegister(String registerId) {
        CafeRegister cafeRegister = cafeRegisterRepository.findById(registerId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 신청글입니다."));
        cafeRegisterRepository.delete(cafeRegister);
    }

}
