package com.grinder.repository;

import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.Member;
import com.grinder.domain.entity.SellerApply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
class SellerApplyRepositoryTest {

    @Autowired
    private SellerApplyRepository sellerApplyRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CafeRepository cafeRepository;

    @DisplayName("판매자 신청 내역 전체 조회")
    @Test
    void testFindAll() {
        for (int i = 0; i < 3; i++) {
            Member member = memberRepository.save(Member.builder().email("test"+i+"@test.com").nickname("member"+i).password("1234").phoneNum("0101234123"+i).build());
            Cafe cafe = cafeRepository.save(Cafe.builder().name("cafe"+i).address("서울시 마포구 서교동 123-"+i).phoneNum("0101234123"+i).build());
            sellerApplyRepository.save(SellerApply.builder().cafe(cafe).member(member).imageUrl("url").build());
        }

        List<SellerApply> applyList = sellerApplyRepository.findAll();

        assertThat(applyList.size()).isEqualTo(3);
    }

}