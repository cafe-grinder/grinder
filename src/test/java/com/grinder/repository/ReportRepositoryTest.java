package com.grinder.repository;

import com.grinder.domain.entity.Member;
import com.grinder.domain.entity.Report;
import com.grinder.domain.enums.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ReportRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 3; i++) {
            Member memberi = memberRepository.save(Member.builder().email("testmember"+i+"@test.com").nickname("member"+i).password("1234").phoneNum("0101234123"+i).build());
            reportRepository.save(Report.builder().member(memberi).contentId(UUID.randomUUID().toString()).contentType(ContentType.FEED).build());
        }
    }

    @DisplayName("컨텐츠 Id로 신고내역 조회")
    @Test
    void testFindByContentId() {
        String contentId = UUID.randomUUID().toString();
        for (int i = 0; i < 3; i++) {
            reportRepository.save(Report.builder().contentType(ContentType.FEED).contentId(contentId).member(memberRepository.findByEmail("testmember"+i+"@test.com").get()).build());
        }

        List<Report> reportList = reportRepository.findByContentId(contentId);

        assertThat(reportList.size()).isEqualTo(3);
    }
}