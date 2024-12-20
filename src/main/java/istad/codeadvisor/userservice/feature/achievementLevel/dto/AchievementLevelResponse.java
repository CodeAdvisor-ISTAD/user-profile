package istad.codeadvisor.userservice.feature.achievementLevel.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public record AchievementLevelResponse(
        String id,
        Integer userId,
        Integer totalPoints,
        String currentLevel

//        boolean userData
) {
}
