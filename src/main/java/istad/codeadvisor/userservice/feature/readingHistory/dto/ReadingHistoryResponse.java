package istad.codeadvisor.userservice.feature.readingHistory.dto;

import istad.codeadvisor.userservice.additional.Content;

import java.time.LocalDateTime;

public record ReadingHistoryResponse(
        String id,
        Integer userId,
        Integer questionId,
        Integer contentId,
        Content content,
        LocalDateTime readAt
) {
}
