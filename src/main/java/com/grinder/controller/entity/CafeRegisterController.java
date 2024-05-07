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

    @GetMapping
    public ResponseEntity<List<FindCafeRegisterDTO>> findAllCafeRegisters() {
        List<FindCafeRegisterDTO> cafeRegisterList = cafeRegisterService.FindAllCafeRegisters();
        return ResponseEntity.ok(cafeRegisterList);
    }

    @DeleteMapping("/{registerId}")
    public ResponseEntity<SuccessResult> denyCafeRegister(@PathVariable String registerId) {
        cafeRegisterService.deleteCafeRegister(registerId);
        return ResponseEntity.ok(new SuccessResult("Delete cafe_register", "신규 장소 신청이 삭제되었습니다."));
    }
}
