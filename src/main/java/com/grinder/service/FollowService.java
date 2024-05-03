package com.grinder.service;

import com.grinder.domain.dto.FollowDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowService {
    public List<FollowDTO.findAllFollowingResponse> findAllFollowingSlice(String email, Pageable pageable);
    public List<FollowDTO.findAllFollowerResponse> findAllFollowerSlice(String email, Pageable pageable);
    public boolean addFollow(String email, String followEmail);
    public boolean deleteFollow(String email, String followEmail);
}
