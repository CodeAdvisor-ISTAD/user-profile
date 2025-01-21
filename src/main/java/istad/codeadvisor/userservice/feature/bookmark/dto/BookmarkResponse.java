package istad.codeadvisor.userservice.feature.bookmark.dto;


public record BookmarkResponse(
        String id,
        String authorUuid,
        String forumUuid,
        String contentUuid,
        boolean isDeleted,
        String createdAt

        ) {
}
