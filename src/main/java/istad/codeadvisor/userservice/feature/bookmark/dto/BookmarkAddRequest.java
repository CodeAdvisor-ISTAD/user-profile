package istad.codeadvisor.userservice.feature.bookmark.dto;

public record BookmarkAddRequest(
        Integer userId,
        Integer forumId,
        Integer contentId
) {
}
