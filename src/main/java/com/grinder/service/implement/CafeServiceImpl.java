package com.grinder.service.implement;

import static com.grinder.domain.dto.CafeRegisterDTO.*;

import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.CafeRegister;
import com.grinder.exception.AlreadyExistException;
import com.grinder.repository.CafeRegisterRepository;
import com.grinder.repository.CafeRepository;
import com.grinder.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {

    private final CafeRepository cafeRepository;
    private final CafeRegisterRepository cafeRegisterRepository;

    @Override
    public void saveCafe(String registerId) {
        CafeRegister register = cafeRegisterRepository.findById(registerId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 신철글입니다."));

        Optional<Cafe> registeredCafeHasName = cafeRepository.findByName(register.getName());
        if (registeredCafeHasName.isPresent()) {
            throw new AlreadyExistException("이미 등록된 카페 이름입니다.");
        }

        Optional<Cafe> registeredCafeHasAddress = cafeRepository.findByAddress(register.getAddress());
        if (registeredCafeHasAddress.isPresent()) {
            throw new AlreadyExistException("이미 등록된 주소입니다.");
        }

        Cafe cafe = Cafe.builder().name(register.getName()).address(register.getAddress()).phoneNum(register.getPhoneNum()).build();

        cafeRepository.save(cafe);
    }

}
