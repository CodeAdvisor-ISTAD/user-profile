package istad.codeadvisor.userservice.feature.achievementLevel.dto;

import jakarta.validation.constraints.NotBlank;

public record AchievementLevelCreateRequest(
        Integer share_content_total,
        Integer ask_question_total,
        Integer answer_question_total,
        Integer comment_total,
        Integer interaction_total
){
}
