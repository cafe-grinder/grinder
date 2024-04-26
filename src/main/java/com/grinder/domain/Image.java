package com.grinder.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "image")
@Builder
public class Image {

    @Id
    @Column(name = "img_id", updatable = false)
    private String imgId;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @Column(name = "img_where")
    private ImgType imgType;

}

