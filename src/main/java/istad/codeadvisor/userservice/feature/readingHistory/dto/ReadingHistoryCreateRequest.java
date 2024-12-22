package istad.codeadvisor.userservice.feature.readingHistory.dto;

public record ReadingHistoryCreateRequest (
        Integer userId,
        Integer contentId,
        Integer questionId

) {
}
