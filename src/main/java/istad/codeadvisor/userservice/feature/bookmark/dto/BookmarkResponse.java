package istad.codeadvisor.userservice.feature.bookmark.dto;

import istad.codeadvisor.userservice.additional.UserData;

public record BookmarkResponse(
        String id,
        String authorUuid,
        String forumUuid,
        String contentUuid,
        boolean isDeleted,
        String createdAt

        ) {
}
