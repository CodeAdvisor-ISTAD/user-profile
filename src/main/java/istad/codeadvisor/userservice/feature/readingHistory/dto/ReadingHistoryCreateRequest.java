package istad.codeadvisor.userservice.feature.readingHistory.dto;

public record ReadingHistoryCreateRequest (
        String userId,
        Integer contentId,
        Integer questionId

) {
}
