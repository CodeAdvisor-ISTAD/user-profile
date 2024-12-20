package istad.codeadvisor.userservice.feature.achievementLevel;

import istad.codeadvisor.userservice.domain.AchievementLevel;
import istad.codeadvisor.userservice.feature.achievementLevel.dto.AchievementLevelResponse;

public interface AchievementLevelService {

    String determineAchievementLevel(Integer totalPoints);

    // Get achievement level by user id
    AchievementLevelResponse getAchievementLevelById(Integer userId);

    int calculateTotalScore(AchievementLevel achievement);
}
