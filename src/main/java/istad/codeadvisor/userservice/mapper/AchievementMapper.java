package istad.codeadvisor.userservice.mapper;

import istad.codeadvisor.userservice.domain.AchievementLevel;
import istad.codeadvisor.userservice.feature.achievementLevel.dto.AchievementLevelResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface AchievementMapper {
    // get achievement
    AchievementLevelResponse toAchievement(AchievementLevel achievement, int totalPoints, String currentLevel);

    AchievementLevelResponse toAchievement(AchievementLevel achievement, int totalPoints);
}
