package com.grinder.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Getter
@NoArgsConstructor
public class SchedulerLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "log_message")
    private String logMessage;

    public SchedulerLog(String logMessage) {
        this.logMessage = logMessage;
    }
}
