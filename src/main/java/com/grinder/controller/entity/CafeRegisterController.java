package com.grinder.controller.entity;

import com.grinder.domain.dto.SuccessResult;
import com.grinder.service.CafeRegisterService;
import com.grinder.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.grinder.domain.dto.CafeRegisterDTO.*;

@RestController
@RequestMapping("/api/cafe_register")
@RequiredArgsConstructor
public class CafeRegisterController {

    private final CafeRegisterService cafeRegisterService;
    private final CafeService cafeService;

}
