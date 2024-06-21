package com.grinder.repository;

import com.grinder.domain.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    @Transactional(isolation = Isolation.READ_COMMITTED)
    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.nickname = :nickname, m.password = :password, m.phoneNum = :phoneNum WHERE m.memberId = :memberId")
    void updateMemberInfo(String memberId, String nickname, String password, String phoneNum);

    @Query("SELECT m FROM Member m")
    List<Member> findMembersForTagUpdate(long limit, long offset);

    @Query("SELECT m FROM Member m")
    List<Member> findMembersForRecommendation(Pageable pageable);
}
