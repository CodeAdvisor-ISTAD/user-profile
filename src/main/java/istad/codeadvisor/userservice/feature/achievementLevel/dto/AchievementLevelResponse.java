package istad.codeadvisor.userservice.feature.achievementLevel.dto;

import java.time.LocalDateTime;

public record AchievementLevelResponse(
        String id,
        String userId,
        String username,
        String currentLevel,// E.g., "Contributor"
        Integer share_content_total,
        Integer ask_question_total,
        Integer answer_question_total,
        Integer comment_total,
        Integer interaction_total,
//        LocalDateTime isPublish,
        Boolean isDeleted,
        Boolean isPublish,
        Integer totalPoints


//        boolean userData
) {
}
