package com.grinder.service;

import com.grinder.domain.entity.Heart;

import java.util.List;

public interface HeartService {
    void addLike(Heart like);
    void deleteLike(String member_id, String content_id, String content_type);
    Heart findLike(String member_id, String content_id, String content_type);
    List<Heart> findByContent(String content_id, String content_type);
}
