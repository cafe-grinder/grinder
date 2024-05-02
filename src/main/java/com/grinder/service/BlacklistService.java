package com.grinder.service;

import com.grinder.domain.dto.BlacklistDTO;

import java.util.List;

public interface BlacklistService {
    public List<BlacklistDTO.findAllResponse> findAllBlacklist(String memberEmail);
    public boolean addBlacklist(BlacklistDTO.AddRequest request, String memberEmail);
    public boolean deleteBlacklist(Long blacklistId, String memberEmail);
}
