package com.grinder.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "image")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @Column(name = "img_id", updatable = false)
    private String imgId;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "img_type")
    private ImgType imgType;

    @PrePersist
    void prePersist() {
        imgId = imgId == null ? UUID.randomUUID().toString() : imgId;
    }
}

