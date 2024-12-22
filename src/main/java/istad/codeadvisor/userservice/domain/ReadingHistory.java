package istad.codeadvisor.userservice.domain;

import istad.codeadvisor.userservice.additional.Content;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "reading_histories")
public class ReadingHistory {
    @Id
    private String id; // Primary key
    private Integer userId;
    private Integer questionId;
    private Integer contentId; // Foreign key for content (can be mapped if needed)
    private Content content;
    private LocalDateTime readAt;
    private Boolean isDeleted;
}
