package com.grinder.controller;

import com.grinder.service.SellerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SellerInfoController {

    private final SellerInfoService sellerInfoService;
}
