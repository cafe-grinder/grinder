package com.grinder.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "seller_apply")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerApply {

    @Id
    @Column(name = "apply_id", updatable = false, length = 36)
    private String applyId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "cafe_id", nullable = false)
    private Cafe cafe;

    @Column(name = "reg_image_url", nullable = false)
    private String regImageUrl;

    @PrePersist
    public void prePersist() {
        applyId = applyId == null ? UUID.randomUUID().toString() : applyId;
    }
}
