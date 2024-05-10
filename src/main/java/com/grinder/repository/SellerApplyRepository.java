package com.grinder.repository;

import com.grinder.domain.entity.SellerApply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerApplyRepository extends JpaRepository<SellerApply, String> {

    Page<SellerApply> findAll(Pageable pageable);
}
