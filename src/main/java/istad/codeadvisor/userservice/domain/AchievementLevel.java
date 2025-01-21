package istad.codeadvisor.userservice.domain;

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
    private String userId;
    private String authorUuid;
    private String username;
    private String currentLevel; // E.g., "Contributor"
    @Builder.Default
    private Integer share_content_total = 0;

    @Builder.Default
    private Integer ask_question_total = 0;

    @Builder.Default
    private Integer answer_question_total = 0;

    @Builder.Default
    private Integer comment_total = 0;

    @Builder.Default
    private Integer interaction_total = 0;
    private Boolean isPublish;
    private Boolean isDeleted;

}
