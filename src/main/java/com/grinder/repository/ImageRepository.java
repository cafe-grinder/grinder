package com.grinder.repository;

import com.grinder.domain.entity.Image;
import com.grinder.domain.enums.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String> {
    List<Image> findByContentIdAndContentType(String contentId, ContentType contentType);
}
