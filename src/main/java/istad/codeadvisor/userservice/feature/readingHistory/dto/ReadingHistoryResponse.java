package istad.codeadvisor.userservice.feature.readingHistory.dto;


import java.time.LocalDateTime;

public record ReadingHistoryResponse(
        String id,
        String authorUuid,
        String forumSlug,
        String contentSlug,
        LocalDateTime createdAt
) {
}
