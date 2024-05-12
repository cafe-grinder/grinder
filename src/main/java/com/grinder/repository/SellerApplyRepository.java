package com.grinder.repository;

import com.grinder.domain.entity.SellerApply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerApplyRepository extends JpaRepository<SellerApply, String> {
    Page<SellerApply> findAll(Pageable pageable);
    Optional<SellerApply> findByMember_MemberIdAndCafe_CafeId(String memberId, String CafeId);
}
