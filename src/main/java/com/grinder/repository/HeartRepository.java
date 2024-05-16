package com.grinder.repository;

import com.grinder.domain.entity.Heart;
import com.grinder.domain.enums.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeartRepository extends JpaRepository<Heart, String> {
    void deleteAllByMember_EmailAndContentIdAndContentType(String memberEmail, String contentId, ContentType contentType);
    List<Heart> findAllByMember_EmailAndContentIdAndContentType(String memberEmail, String contentId, ContentType contentType);
    List<Heart> findByContentIdAndContentType(String contentId, ContentType contentType);
    Long countByContentTypeAndContentId(ContentType contentType, String ContentId);
}
