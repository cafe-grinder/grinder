package com.grinder.domain.entity;

import com.grinder.domain.enums.Weekday;
import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "opening_hours")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpeningHours {

    @Id
    @ManyToOne
    @JoinColumn(name = "cafe_id", updatable = false, nullable = false)
    private Cafe cafe;

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
