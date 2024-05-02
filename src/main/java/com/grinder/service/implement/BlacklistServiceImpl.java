package com.grinder.service.implement;

import com.grinder.domain.dto.BlacklistDTO;
import com.grinder.domain.entity.Blacklist;
import com.grinder.domain.entity.Member;
import com.grinder.exception.MaximumRangeAlreadyAddedException;
import com.grinder.repository.BlacklistRepository;
import com.grinder.repository.MemberRepository;
import com.grinder.repository.queries.BlacklistQueryRepository;
import com.grinder.service.BlacklistService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BlacklistServiceImpl implements BlacklistService {

    private final MemberRepository memberRepository;
    private final BlacklistRepository blacklistRepository;
    private final BlacklistQueryRepository blacklistQueryRepository;

    @Transactional
    public List<BlacklistDTO.findAllResponse> findAllBlacklist(String memberEmail) {
        return blacklistQueryRepository.findAllBlacklistByMemberEmail(memberEmail);
    }

    public boolean addBlacklist(BlacklistDTO.AddRequest request, String memberEmail) {
        request.setMemberEmail(memberEmail);
        Member member = memberRepository.findByEmail(request.getMemberEmail()).orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));
        if (blacklistRepository.countAllByMember(member) >= 10) throw new MaximumRangeAlreadyAddedException("이미 최대 범위로 추가되었습니다.");
        Member blockedMember = memberRepository.findByEmail(request.getBlockedMemberEmail()).orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        if (member.getMemberId().equals(blockedMember.getMemberId())) throw new IllegalArgumentException("자기 자신을 추가할 수 없습니다.");

        blacklistRepository.save(Blacklist.builder().member(member).blockedMember(blockedMember).build());
        return true;
    }

    @Override
    public boolean deleteBlacklist(Long blacklistId, String memberEmail) {
        Blacklist blacklist = blacklistRepository.findById(blacklistId).orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));
        if (!memberEmail.equals(blacklist.getMember().getEmail())) throw new IllegalArgumentException("데이터는 본인만 삭제 가능합니다.");
        blacklistRepository.delete(blacklist);
        return true;
    }
}
