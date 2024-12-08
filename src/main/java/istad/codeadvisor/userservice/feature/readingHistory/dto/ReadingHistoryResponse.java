package istad.codeadvisor.userservice.feature.readingHistory.dto;

import java.time.LocalDateTime;

public record ReadingHistoryResponse(
        Integer id,
        Integer userId,
        Integer questionId,
        Integer contentId,
        LocalDateTime readAt
) {
}
