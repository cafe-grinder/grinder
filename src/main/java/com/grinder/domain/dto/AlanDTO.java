package com.grinder.domain.dto;

import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.CafeSummary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AlanDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class AlanResponse {
        private String actionName;
        private String actionSpeak;
        private String content;
    }
}
