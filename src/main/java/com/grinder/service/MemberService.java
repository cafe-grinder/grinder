package com.grinder.service;

import com.grinder.domain.entity.Member;
import java.util.List;
import static com.grinder.domain.dto.MemberDTO.*;

public interface MemberService {

    public List<FindMemberDTO> findAllMembers();

    public Member findMemberById(String memberId);

    public void updateMemberRole(String memberId);

    public void updateMemberIsDeleted(String memberId);

    public List<FindMemberDTO> searchMemberByNickname(String nickname);
}