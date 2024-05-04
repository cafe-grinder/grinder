package com.grinder.controller.entity;

import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.CafeRegisterService;
import com.grinder.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.grinder.domain.dto.CafeRegisterDTO.*;

@RestController
@RequestMapping("/api/cafe_register")
@RequiredArgsConstructor
public class CafeRegisterController {

    private final CafeRegisterService cafeRegisterService;
    private final CafeService cafeService;

    @GetMapping("/find")
    public ResponseEntity<List<FindCafeRegisterDTO>> findAllCafeRegisters() {
        List<FindCafeRegisterDTO> cafeRegisterList = cafeRegisterService.FindAllCafeRegisters();

        return ResponseEntity.ok(cafeRegisterList);
    }

    @DeleteMapping("/accept/{registerId}")
    public ResponseEntity<SuccessResult> acceptCafeRegister(@PathVariable String registerId) {
        cafeService.saveCafe(registerId);
        cafeRegisterService.deleteCafeRegister(registerId);
        return ResponseEntity.ok(new SuccessResult("Success", "요청이 성공적으로 처리되었습니다."));
    }

    @DeleteMapping("/deny/{registerId}")
    public ResponseEntity<SuccessResult> denyCafeRegister(@PathVariable String registerId) {
        cafeRegisterService.deleteCafeRegister(registerId);
        return ResponseEntity.ok(new SuccessResult("Success", "신규 장소 등록을 거절했습니다."));
    }
}
