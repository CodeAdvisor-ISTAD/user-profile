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
    private String userId; // Reference to the UserProfile service
    private String authorUuid; // Reference to the author of the content
    @Builder.Default
    private String forumId = null;
    @Builder.Default
    private String contentId = null; // Unique identifier for the content
    private LocalDateTime bookmarkedAt; // Timestamp when the content was bookmarked
    private Boolean isDeleted;


}
