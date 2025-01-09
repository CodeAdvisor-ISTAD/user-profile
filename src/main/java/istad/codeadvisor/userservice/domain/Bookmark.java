package istad.codeadvisor.userservice.domain;

import istad.codeadvisor.userservice.additional.UserData;
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
    private UserData userData;
    @Builder.Default
    private Integer forumId = null;
    @Builder.Default
    private Integer contentId = null; // Unique identifier for the content
    private LocalDateTime bookmarkedAt; // Timestamp when the content was bookmarked
    private Boolean isDeleted;


}
