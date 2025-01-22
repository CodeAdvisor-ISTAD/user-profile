package istad.codeadvisor.userservice.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "bookmarks")
public class Bookmark {
    @Id
    private String id; // Unique identifier for the bookmark
    private String authorUuid; // Reference to the UserProfile service
    @Builder.Default
    private String forumSlug = null;
    @Builder.Default
    private String contentSlug = null; // Unique identifier for the content
    private LocalDateTime createdAt; // Timestamp when the content was bookmarked
    @Builder.Default
    private Boolean isDeleted = false; // Flag to mark the bookmark as deleted
    @Builder.Default
    private Boolean isBookmarked = true; // Flag to mark the bookmark as bookmarked
}
