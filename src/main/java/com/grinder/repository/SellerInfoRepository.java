package com.grinder.repository;

import com.grinder.domain.entity.Member;
import com.grinder.domain.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerInfoRepository extends JpaRepository<SellerInfo, Long> {
    List<SellerInfo> findAllByMember(Member member);
}
