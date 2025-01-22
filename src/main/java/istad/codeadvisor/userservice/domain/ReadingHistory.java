package istad.codeadvisor.userservice.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "reading_histories")
public class ReadingHistory {
    @Id
    private String id; // Primary key
    private String authorUuid; // Reference to the UserProfile service
    private String forumSlug;
    private String contentSlug; // Foreign key for content (can be mapped if needed)
    private LocalDateTime createdAt;
}
