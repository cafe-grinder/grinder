package com.grinder.domain.entity;

import com.grinder.domain.enums.Weekday;
import jakarta.persistence.*;
import java.time.LocalDateTime;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", nullable = false)
    private Cafe cafeId;

    @Column(name = "weekday", nullable = false)
    private Weekday weekday;

    @Column(name = "open_time")
    private LocalDateTime openTime;

    @Column(name = "close_time")
    private LocalDateTime closeTime;

    @Column(name = "is_holiday", nullable = false)
    private Boolean isHoliday;

}
