package istad.codeadvisor.userservice.feature.bookmark.dto;


public record BookmarkResponse(
        String id,
        String authorUuid,
        String forumSlug,
        String contentSlug,
        Boolean isBookmarked,
        boolean isDeleted,
        String createdAt

        ) {
}
