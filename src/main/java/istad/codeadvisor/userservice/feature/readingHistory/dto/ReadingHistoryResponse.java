package istad.codeadvisor.userservice.feature.readingHistory.dto;


import java.time.LocalDateTime;

public record ReadingHistoryResponse(
        String id,
        String userId,
        Integer questionId,
        Integer contentId,
        LocalDateTime readAt
) {
}
