package com.grinder.repository;

import com.grinder.domain.entity.Image;
import com.grinder.domain.enums.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    List<Image> findByContentIdAndContentType(String contentId, ContentType contentType);
    Optional<Image> findByContentId(String contentId);
    Optional<List<Image>> findAllByContentTypeAndContentId(ContentType type, String contentId);
    Optional<Image> findByContentTypeAndContentId(ContentType type, String contentId);
}
