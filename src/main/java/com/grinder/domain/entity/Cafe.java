package com.grinder.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "cafe")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cafe extends BaseEntity {

    @Id
    @Column(name = "cafe_id", updatable = false, length = 36)
    private String cafeId;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "phone_num", nullable = false, length = 11)
    private String phoneNum;

    @Column(name = "average_grade", nullable = false)
    private Integer averageGrade;

    @Column(name = "reg_image_url")
    private String regImageUrl;

    @PrePersist
    public void prePersist() {
        cafeId = cafeId == null ? UUID.randomUUID().toString() : cafeId;
        averageGrade = averageGrade == null ? 0 : averageGrade;
    }

    public void uploadRegImage(String regImageUrl) {
        this.regImageUrl = regImageUrl;
    }
}
