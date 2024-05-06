package com.grinder.service;

import com.grinder.domain.entity.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;
import static com.grinder.domain.dto.MemberDTO.*;

public interface MemberService {

    Member findMemberById(String memberId);

    void updateMemberRole(String memberId);

    void updateMemberIsDeleted(String memberId);

    List<FindMemberDTO> searchMemberSlice(String role, String nickname, Pageable pageable);

    Member findMemberByEmail(String email);

    public boolean addMember(MemberRequestDto request);
}
