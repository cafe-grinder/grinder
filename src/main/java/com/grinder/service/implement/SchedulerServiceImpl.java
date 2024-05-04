package com.grinder.service.implement;

import com.grinder.service.SchedulerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Scheduled(cron = "0 0 0 0 * *", zone = "Asia/Seoul")
    public void DecreaseFeedRank() {

    }
}
