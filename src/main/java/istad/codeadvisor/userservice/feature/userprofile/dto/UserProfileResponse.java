package istad.codeadvisor.userservice.feature.userprofile.dto;

import istad.codeadvisor.userservice.domain.AchievementLevel;
import istad.codeadvisor.userservice.feature.achievementLevel.dto.AchievementLevelResponse;

public record UserProfileResponse (
        String id,
//        String familyName,
//        String givenName,
        String fullName,
        String authorUuid,
        String username,
        String gender,
        String bio,
        String phoneNumber,
        String jobPosition,
        String school,
        String workPlace,
        String profileImage,
        String dob,
        String pob,
        String coverColor,
        AchievementLevelResponse achievementLevel
) {
}
