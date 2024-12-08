package istad.codeadvisor.userservice.feature.readingHistory.dto;

import java.time.LocalDateTime;

public record ReadingHistoryUpdateRequest(
        LocalDateTime readAt
) {
}
