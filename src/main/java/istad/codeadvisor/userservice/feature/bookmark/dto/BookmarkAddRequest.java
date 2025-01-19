package istad.codeadvisor.userservice.feature.bookmark.dto;

import org.springframework.lang.Nullable;

public record BookmarkAddRequest(
        @Nullable
        String forumSlug,
        @Nullable
        String contentSlug
) {
}
