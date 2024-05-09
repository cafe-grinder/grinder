package com.grinder.service.implement;

import com.grinder.service.SchedulerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Scheduled(cron = "0 0 0 ? * SUN", zone = "Asia/Seoul")
    public void decreaseFeedRank() {
        //TODO : 카페 평균 별점 로직(하루에 한번)

    }
}
