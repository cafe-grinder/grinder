package com.grinder.domain.entity;

import com.grinder.domain.enums.ContentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "image", indexes = {
        @Index(name = "idx_image_content_type", columnList = "content_type")
})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @Column(name = "image_id", updatable = false, length = 36)
    private String imageId;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "content_id", nullable = false, length = 36)
    private String contentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false, length = 16)
    private ContentType contentType;

    @PrePersist
    public void prePersist() {
        imageId = imageId == null ? UUID.randomUUID().toString() : imageId;
    }

    public void updateContentId(String contentId) {
        this.contentId = contentId;
    }
}

