package istad.codeadvisor.userservice.feature.bookmark.dto;

import istad.codeadvisor.userservice.additional.UserData;

public record BookmarkResponse(
        String id,
        Integer userId,
        Integer forumId,
        UserData userData,
        Integer contentId,
        boolean isDeleted,
        String bookmarkedAt

        ) {
}
