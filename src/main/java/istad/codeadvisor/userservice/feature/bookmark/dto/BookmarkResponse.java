package istad.codeadvisor.userservice.feature.bookmark.dto;

public record BookmarkResponse(
        String id,
        Integer userId,
        Integer forumId,
        Integer contentId,
        boolean isDeleted,
        String bookmarkedAt

        ) {
}
