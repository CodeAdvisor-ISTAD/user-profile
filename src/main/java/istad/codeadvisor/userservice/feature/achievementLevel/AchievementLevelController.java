package istad.codeadvisor.userservice.feature.achievementLevel;

import istad.codeadvisor.userservice.feature.achievementLevel.dto.AchievementLevelResponse;
import istad.codeadvisor.userservice.feature.userprofile.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/achievement_levels")
public class AchievementLevelController {
    private final AchievementLevelService achievementLevelService;

    // Get the achievement level by me
    @GetMapping("/me")
    public AchievementLevelResponse getMe(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getClaimAsString("userId");
        return achievementLevelService.getAchievementLevelByUserId(userId);
    }

    // Get the achievement level by username
    @CrossOrigin(origins = "http://127.0.0.1:8168/**")
    @GetMapping("/{userId}")
    public AchievementLevelResponse getAchievementLevelByUserId(@PathVariable String userId) {
        // Call the service method and return the result
        return achievementLevelService.getAchievementLevelByUserId(userId);
    }

    // Disable the achievement level by username
    @PatchMapping("/{username}/disable")
    public void disableAchievementLevel(@PathVariable String username) {
        achievementLevelService.disableAchievementLevel(username);
    }

    // Enable the achievement level by username
    @PatchMapping("/{username}/enable")
    public void enableAchievementLevel(@PathVariable String username) {
        achievementLevelService.enableAchievementLevel(username);
    }

    // IsPublic: true
    @PatchMapping("/is-public")
    public void isPublic(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("username");
        achievementLevelService.isPublic(username);
    }

    // IsUnpublished
    @PatchMapping("/is-unpublished")
    public void isUnpublished(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("username");
        achievementLevelService.isUnpublished(username);
    }


}
