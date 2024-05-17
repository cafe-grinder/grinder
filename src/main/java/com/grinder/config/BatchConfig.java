package com.grinder.config;

import com.grinder.domain.entity.SchedulerLog;
import com.grinder.repository.SchedulerLogRepository;
import com.grinder.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final SchedulerService schedulerService;
    private final SchedulerLogRepository schedulerLogRepository;


    @Bean
    public Job logJob() {
        return new JobBuilder("logJob", jobRepository)
                .start(logStep())
                .build();
    }

    @Bean
    public Step logStep() {
        return new StepBuilder("logStep", jobRepository)
                .tasklet(logTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet logTasklet() {
        return (contribution, chunkContext) -> {
            // Scheduler에서 로그 리스트 가져오기
            List<String> logList = schedulerService.getLogList();

            // 로그 데이터를 데이터베이스에 저장 (여기서는 간단히 출력으로 대체)
            logList.forEach(message -> schedulerLogRepository.save(new SchedulerLog(message)));

            // 로그 리스트 초기화
            schedulerService.clearLogList();

            return RepeatStatus.FINISHED;
        };
    }
}
