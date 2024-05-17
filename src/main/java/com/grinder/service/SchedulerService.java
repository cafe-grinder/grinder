package com.grinder.service;

import com.grinder.domain.entity.Cafe;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface SchedulerService {

    public void CalAverage();

    void updateAverageGradeForCafes(int dayOfWeek);

    void calculateAndSetAverageGrade(Cafe cafe);

    void updateTagListForMembers(int dayOfWeek);

    @Scheduled(cron = "0 0 1 * * ?", zone = "Asia/Seoul")
    void recommendAlan();

    void recommendCafeForMembers(int dayOfMonth);

    void updateRanks(int dayOfMonth);

    @Scheduled(cron = "0 0 2 * * ?", zone = "Asia/Seoul")
    void updateRank();

    void executeWithRetry(Runnable task, String taskName);

    List<String> getLogList();

    void clearLogList();

    @Scheduled(cron = "0 0 3 * * ?", zone = "Asia/Seoul")
    void runLogJob();
}
