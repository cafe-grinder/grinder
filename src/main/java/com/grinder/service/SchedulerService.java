package com.grinder.service;

import com.grinder.domain.entity.Cafe;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public interface SchedulerService {

    void performCalAverageTask();

    void updateAverageGradeForCafes(int dayOfWeek);

    void calculateAndSetAverageGrade(Cafe cafe);

    void updateTagListForMembers(int dayOfWeek);

    @Scheduled(cron = "0 0 1 * * ?", zone = "Asia/Seoul")
    void recommendAlan();

    void performRecommendAlanTask();

    void recommendCafeForMembers(int dayOfMonth);

    void updateRanks(int dayOfMonth);

    @Scheduled(cron = "0 0 2 * * ?", zone = "Asia/Seoul")
    void updateRank();

    void performUpdateRankTask();

    void executeWithRetry(Runnable task, String taskName);

    List<String> getLogList();

    void clearLogList();
}
