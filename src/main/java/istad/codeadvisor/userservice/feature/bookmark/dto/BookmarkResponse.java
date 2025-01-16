package istad.codeadvisor.userservice.feature.bookmark.dto;

public record BookmarkResponse(
        String id,
        String userId,
        String authorUuid,
        String forumId,
        String contentId,
        boolean isDeleted,
        String bookmarkedAt

        ) {
}
