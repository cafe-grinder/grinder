package com.grinder.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class OpeningHoursDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class saveOpeningRequest {
        private String day;
        private String openTime;
        private String closeTime;
        private Boolean isHoliday;

        public LocalTime getOpenTime() {
            if (isHoliday) {
                return null; // 또는 특정 기본값
            }
            try {
                return LocalTime.parse(openTime, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                return null;
            }
        }

        public LocalTime getCloseTime() {
            if (isHoliday) {
                return null; // 또는 특정 기본값
            }
            try {
                return LocalTime.parse(closeTime, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                return null;
            }
        }
    }
}
