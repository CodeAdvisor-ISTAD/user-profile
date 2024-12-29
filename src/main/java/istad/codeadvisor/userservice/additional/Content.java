package istad.codeadvisor.userservice.additional;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "content")
public class Content {
    private String userId;
    private String contentId;
    private String reactionType;
}
