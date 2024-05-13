package com.grinder.repository;

import com.grinder.domain.entity.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.nickname = :nickname, m.password = :password, m.phoneNum = :phoneNum WHERE m.memberId = :memberId")
    void updateMemberInfo(String memberId, String nickname, String password, String phoneNum);

}
