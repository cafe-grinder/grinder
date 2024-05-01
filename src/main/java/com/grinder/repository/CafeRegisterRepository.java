package com.grinder.repository;

import com.grinder.domain.entity.CafeRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeRegisterRepository extends JpaRepository<CafeRegister, String> {
}
