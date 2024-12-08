package istad.codeadvisor.userservice.feature.achievementLevel.dto;

public record AchievementLevelCreateRequest(
        String currentLevel,
        Integer share_content_points,
        Integer ask_question_points,
        Integer answer_question_points,
        Integer comment_points,
        Integer interaction_point
){
}
