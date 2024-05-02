package com.grinder.repository;

import com.grinder.domain.entity.Image;
import com.grinder.domain.enums.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    List<Image> findByContentIdAndContentType(String contentId, ContentType contentType);
}
