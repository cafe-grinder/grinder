package com.grinder.service.implement;

import com.grinder.domain.dto.BlacklistDTO;
import com.grinder.domain.entity.Blacklist;
import com.grinder.domain.entity.Member;
import com.grinder.repository.BlacklistRepository;
import com.grinder.repository.MemberRepository;
import com.grinder.repository.queries.BlacklistQueryRepository;
import com.grinder.service.implement.BlacklistServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({BlacklistServiceImpl.class, BlacklistQueryRepository.class}) // 서비스 레이어 명시적으로 포함
class BlacklistServiceImplTest {
    @Autowired
    public BlacklistServiceImpl blacklistService;
    @Autowired
    public BlacklistRepository blacklistRepository;
    @Autowired
    public MemberRepository memberRepository;
    @Autowired
    public BlacklistQueryRepository blacklistQueryRepository;

    @Test
    void findAllBlacklist() {
        Member member1 = memberRepository.save(Member.builder().email("test1@test.com").nickname("test-user-1").password("1234").phoneNum("01012345678").build());
        Member member2 = memberRepository.save(Member.builder().email("test2@test.com").nickname("test-user-2").password("1234").phoneNum("01012345678").build());
        blacklistRepository.save(Blacklist.builder().member(member1).blockedMember(member2).build());

        List<BlacklistDTO.findAllResponse> list = blacklistService.findAllBlacklist(member1.getEmail());

        assertEquals(list.get(0).getBlockedEmail(), member2.getEmail());
    }

    @Test
    void addBlacklist() {
        Member member1 = memberRepository.save(Member.builder().email("test1@test.com").nickname("test-user-1").password("1234").phoneNum("01012345678").build());
        Member member2 = memberRepository.save(Member.builder().email("test2@test.com").nickname("test-user-2").password("1234").phoneNum("01012345678").build());
        BlacklistDTO.AddRequest request = new BlacklistDTO.AddRequest();
        request.setMemberEmail(member1.getEmail());
        request.setBlockedMemberEmail(member2.getEmail());

        boolean result = blacklistService.addBlacklist(request, member1.getEmail());

        assertTrue(result);
    }

    @Test
    void deleteBlacklist() {
        Member member1 = memberRepository.save(Member.builder().email("test1@test.com").nickname("test-user-1").password("1234").phoneNum("01012345678").build());
        Member member2 = memberRepository.save(Member.builder().email("test2@test.com").nickname("test-user-2").password("1234").phoneNum("01012345678").build());
        BlacklistDTO.AddRequest request = new BlacklistDTO.AddRequest();
        request.setMemberEmail(member1.getEmail());
        request.setBlockedMemberEmail(member2.getEmail());

        blacklistService.addBlacklist(request, member1.getEmail());
        boolean result = blacklistService.deleteBlacklist(blacklistRepository.findAll().get(0).getBlacklistId(), member1.getEmail());

        assertTrue(result);
    }
}