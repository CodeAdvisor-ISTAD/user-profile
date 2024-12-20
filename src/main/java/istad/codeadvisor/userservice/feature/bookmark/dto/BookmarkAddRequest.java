package istad.codeadvisor.userservice.feature.bookmark.dto;

import istad.codeadvisor.userservice.additional.UserData;

public record BookmarkAddRequest(
        Integer userId,
        Integer forumId,
        UserData userData,
        Integer contentId
) {
}
