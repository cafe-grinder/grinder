package com.grinder.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "cafe")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cafe extends BaseEntity {

    @Id
    @Column(name = "cafe_id", updatable = false, length = 36)
    private String cafeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member user;

    @OneToOne
    @JoinColumn(name = "img_id")
    private Image image;

    @Column(name = "name", nullable = false, unique = true, length = 20)
    private String name;

    @Column(name = "address", nullable = false, unique = true, length = 100)
    private String address;

    @PrePersist
    public void prePersist() {
        cafeId = cafeId == null ? UUID.randomUUID().toString() : cafeId;
    }
}
