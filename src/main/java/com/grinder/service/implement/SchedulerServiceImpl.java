package com.grinder.service.implement;

import com.grinder.domain.entity.Cafe;
import com.grinder.repository.CafeRepository;
import com.grinder.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {
    private final CafeRepository cafeRepository;

    //매일 0시에 전체 카페의 1/7만 평균 별점 업데이트 진행
    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Seoul")
    public void CalAverage() {
        var today = LocalDate.now();
        int dayOfWeek = today.getDayOfWeek().getValue();  // 1 (Monday) to 7 (Sunday)
        updateAverageGradeForCafes(dayOfWeek);
    }

    private void updateAverageGradeForCafes(int dayOfWeek) {
        // 전체 카페 수 조회
        long totalCafes = cafeRepository.count();
        long batchSize = totalCafes / 7;  // 한 번에 처리할 카페 수
        long offset = (dayOfWeek - 1) * batchSize;  // 주중의 날에 따라 오프셋 조정

        // 해당 요일에 처리할 카페 리스트 가져오기
        List<Cafe> cafes = cafeRepository.findCafesForAverageCalculation(batchSize, offset);
        cafes.forEach(this::calculateAndSetAverageGrade);
    }

    private void calculateAndSetAverageGrade(Cafe cafe) {
        Double average = cafeRepository.findAverageGradeByCafeId(cafe.getCafeId());
        cafe.setAverageGrade(average.intValue());
        cafeRepository.save(cafe);
    }
}
