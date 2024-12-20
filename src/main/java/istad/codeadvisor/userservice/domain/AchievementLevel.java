package istad.codeadvisor.userservice.domain;

import istad.codeadvisor.userservice.additional.UserData;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "achievement_levels")
public class AchievementLevel {
    @Id
    private String id;
    private Integer userId;
    private UserData userData;
    private String currentLevel; // E.g., "Contributor"
    private Integer share_content_total;
    private Integer ask_question_total;
    private Integer answer_question_total;
    private Integer comment_total;
    private Integer interaction_total;
    private Boolean isPublish;
    private Boolean isDeleted;
}
