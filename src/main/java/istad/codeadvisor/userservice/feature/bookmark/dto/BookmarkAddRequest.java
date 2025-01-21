package istad.codeadvisor.userservice.feature.bookmark.dto;

import org.springframework.lang.Nullable;
public record BookmarkAddRequest(
        @Nullable
        String forumUuid,
        @Nullable
        String contentUuid
) {
}
