package com.grinder.repository;

import com.grinder.domain.entity.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("select m from Member m where m.nickname like %:nickname%")
    List<Member> searchMemberByNickname(String nickname);
}
