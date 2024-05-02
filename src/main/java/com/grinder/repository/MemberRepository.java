package com.grinder.repository;

import com.grinder.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("select m from Member m where m.nickname like %:nickname%")
    List<Member> searchMemberByNickname(@Param("nickname") String nickname);

    Optional<Member> findByEmail(String email);
}
