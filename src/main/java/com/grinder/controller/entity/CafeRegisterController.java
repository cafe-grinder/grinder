package com.grinder.controller.entity;

import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.CafeRegisterService;
import com.grinder.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Slice<FindCafeRegisterDTO>> findAllCafeRegisters(@PageableDefault(size = 5) Pageable pageable) {
        Slice<FindCafeRegisterDTO> cafeRegisterSlice = cafeRegisterService.FindAllCafeRegisters(pageable);
        return ResponseEntity.ok(cafeRegisterSlice);
    }

    @DeleteMapping("/{registerId}")
    public ResponseEntity<SuccessResult> denyCafeRegister(@PathVariable String registerId) {
        cafeRegisterService.deleteCafeRegister(registerId);
        return ResponseEntity.ok(new SuccessResult("Delete cafe_register", "신규 장소 신청이 삭제되었습니다."));
    }
}
