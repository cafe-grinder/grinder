package com.grinder.domain.entity;

import com.grinder.domain.enums.Weekday;
import jakarta.persistence.*;
import java.time.LocalTime;

import lombok.*;

@Entity
@Table(name = "opening_hours", indexes = {
        @Index(name = "idx_weekday", columnList = "weekday")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpeningHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opening_id", updatable = false)
    private Long openingId;

    @Column(name = "cafe_id", updatable = false, nullable = false)
    private String cafeId;

    @Enumerated(EnumType.STRING)
    @Column(name = "weekday", nullable = false, length = 10)
    private Weekday weekday;

    @Column(name = "open_time")
    private LocalTime openTime;

    @Column(name = "close_time")
    private LocalTime closeTime;

    @Column(name = "is_holiday", nullable = false)
    private Boolean isHoliday;
}
