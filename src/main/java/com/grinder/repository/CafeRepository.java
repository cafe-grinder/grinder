package com.grinder.repository;

import com.grinder.domain.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, String> {

    Optional<Cafe> findByName(String name);
    Optional<Cafe> findByAddress(String address);
    List<Cafe> findByNameContainingIgnoreCase(String name);
    @Query("SELECT c FROM Cafe c")
    List<Cafe> findCafesForAverageCalculation(long limit, long offset);

    @Query("SELECT AVG(f.grade) FROM Feed f WHERE f.cafe.cafeId = :cafeId AND f.isVisible = true")
    Double findAverageGradeByCafeId(String cafeId);
}
