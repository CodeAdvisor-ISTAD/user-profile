package istad.codeadvisor.userservice.mapper;

import istad.codeadvisor.userservice.domain.AchievementLevel;
import istad.codeadvisor.userservice.domain.UserProfile;
import istad.codeadvisor.userservice.feature.achievementLevel.dto.AchievementLevelResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface AchievementMapper {
//    AchievementLevelResponse fromAchievement(AchievementLevelCreateRequest achievementLevelCreateRequest);
    AchievementLevel fromUserProfile(UserProfile userProfile);
    AchievementLevelResponse toUserProfile(UserProfile userProfile);
    // get achievement
    AchievementLevelResponse toAchievement(AchievementLevel achievement, Integer totalPoints, String currentLevel);

}
