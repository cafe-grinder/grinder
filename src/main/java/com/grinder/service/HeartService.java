package com.grinder.service;

import com.grinder.domain.dto.HeartDTO;
import com.grinder.domain.entity.Heart;

import java.util.List;

public interface HeartService {
    Heart addHeart(String memberEmail, HeartDTO.HeartRequestDTO request);
    void deleteHeart(String memberEmail, HeartDTO.HeartRequestDTO request);
    List<Heart> findHeart(String memberEmail, HeartDTO.HeartRequestDTO request);
    List<Heart> findHeartList(HeartDTO.HeartRequestDTO request);
    boolean isHeart(String memberEmail, HeartDTO.HeartRequestDTO request);
}
