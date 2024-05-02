package com.grinder.service.implement;

import com.grinder.repository.SellerInfoRepository;
import com.grinder.service.SellerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerInfoServiceImpl implements SellerInfoService {

    private final SellerInfoRepository sellerInfoRepository;
}
