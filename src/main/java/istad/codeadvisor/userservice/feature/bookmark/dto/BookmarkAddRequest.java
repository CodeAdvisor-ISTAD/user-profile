package istad.codeadvisor.userservice.feature.bookmark.dto;

import org.springframework.lang.Nullable;
import istad.codeadvisor.userservice.additional.UserData;

public record BookmarkAddRequest(
        @Nullable
        String forumUuid,
        @Nullable
        String contentUuid
) {
}
