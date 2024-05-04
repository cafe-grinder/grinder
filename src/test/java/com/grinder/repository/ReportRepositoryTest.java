package com.grinder.repository;

import com.grinder.domain.entity.Member;
import com.grinder.domain.entity.Report;
import com.grinder.domain.enums.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReportRepositoryTest {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("신고 내역 전체 조회")
    @Test
    void testFindAll() {
        for (int i = 0; i < 3; i++) {
            Member member = memberRepository.save(Member.builder().email("test"+i+"@test.com").nickname("member"+i).password("1234").phoneNum("0101234123"+i).build());
            reportRepository.save(Report.builder().member(member).contentId(UUID.randomUUID().toString()).contentType(ContentType.FEED).build());
        }

        List<Report> reportList = reportRepository.findAll();

        assertThat(reportList.size()).isEqualTo(3);
    }
}