package com.grinder.repository;

import com.grinder.domain.entity.OpeningHours;
import com.grinder.domain.enums.Weekday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Long> {
    Optional<OpeningHours> findByWeekdayAndCafeId(String cafeId, Weekday weekday);
}
