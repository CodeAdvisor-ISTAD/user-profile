package istad.codeadvisor.userservice.feature.bookmark.dto;

import istad.codeadvisor.userservice.additional.UserData;

public record BookmarkAddRequest(
        String userId,
        Integer forumId,
        UserData userData,
        Integer contentId
) {
}
