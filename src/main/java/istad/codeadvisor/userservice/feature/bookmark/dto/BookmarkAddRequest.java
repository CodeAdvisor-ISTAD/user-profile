package istad.codeadvisor.userservice.feature.bookmark.dto;

public record BookmarkAddRequest(
        String userId,
        String authorUuid,
        String forumId,
        String contentId
) {
}
