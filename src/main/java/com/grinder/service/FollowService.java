package com.grinder.service;

import com.grinder.domain.dto.FollowDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface FollowService {
    Slice<FollowDTO.findAllFollowingResponse> findAllFollowingSlice(String email, Pageable pageable);
    Slice<FollowDTO.findAllFollowerResponse> findAllFollowerSlice(String email, Pageable pageable);
    boolean addFollow(String email, String followEmail);
    boolean deleteFollow(String email, String followEmail);
    boolean existFollow(String email, String followEmail);
}
