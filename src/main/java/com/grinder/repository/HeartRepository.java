package com.grinder.repository;

import com.grinder.domain.entity.Heart;
import com.grinder.domain.enums.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    void deleteByMember_MemberIdAndContentIdAndContentType(String memberId, String contentId, ContentType contentType);
    Heart findByMember_MemberIdAndContentIdAndContentType(String memberId, String contentId, ContentType contentType);
    List<Heart> findByContentIdAndContentType(String contentId, ContentType contentType);
}
