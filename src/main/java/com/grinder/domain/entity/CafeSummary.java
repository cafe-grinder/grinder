package com.grinder.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cafe_summary")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeSummary extends BaseEntity {

    @Id
    @Column(name = "cafe_id", updatable = false, length = 36)
    private String cafeId;

    @Column(name = "summary")
    private String summary;
}
