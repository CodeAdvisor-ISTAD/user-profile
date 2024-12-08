package istad.codeadvisor.userservice.feature.readingHistory.dto;

import java.time.LocalDateTime;

public record ReadingHistoryCreateRequest(
        LocalDateTime readAt
) {
}
