package com.grinder.service;

import com.grinder.domain.dto.MemberDTO;
import com.grinder.domain.entity.Member;

import java.util.List;

public interface MemberService {

    public List<MemberDTO.FindMemberDTO> findAllMembers();

    public Member findMemberById(String memberId);

    public void updateMemberRole(String memberId);

    public void updateMemberIsDeleted(String memberId);

    public List<MemberDTO.FindMemberDTO> searchMemberByNickname(String nickname);
}
