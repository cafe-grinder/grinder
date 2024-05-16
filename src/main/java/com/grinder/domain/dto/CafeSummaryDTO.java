package com.grinder.domain.dto;

import com.grinder.domain.entity.Cafe;
import com.grinder.domain.entity.CafeSummary;
import com.grinder.repository.CafeSummaryRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

public class CafeSummaryDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CafeSummaryResponse {
        private String cafeName;
        private String cafeAddress;
        private String updateTime;
        private String summary;

        public CafeSummaryResponse(Cafe cafe, CafeSummary summary) {
            cafeName = cafe.getName();
            cafeAddress = cafe.getAddress();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            updateTime = summary.getUpdatedAt().format(formatter);
            this.summary = summary.getSummary();
        }
    }
}
