package com.grinder.repository;

import com.grinder.domain.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, String> {

    Optional<Cafe> findByName(String name);
    Optional<Cafe> findByAddress(String address);
}
