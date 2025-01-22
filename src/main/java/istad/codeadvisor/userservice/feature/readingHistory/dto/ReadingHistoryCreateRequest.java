package istad.codeadvisor.userservice.feature.readingHistory.dto;

import org.springframework.lang.Nullable;

public record ReadingHistoryCreateRequest (
        @Nullable
        String contentSlug,
        @Nullable
        String forumSlug

) {
}
