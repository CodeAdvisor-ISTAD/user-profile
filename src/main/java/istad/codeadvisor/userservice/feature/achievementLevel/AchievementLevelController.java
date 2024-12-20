package istad.codeadvisor.userservice.feature.achievementLevel;

import istad.codeadvisor.userservice.feature.achievementLevel.dto.AchievementLevelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/achievement-levels")
public class AchievementLevelController {
    private final AchievementLevelService achievementLevelService;

    @GetMapping("/{userId}")
    public AchievementLevelResponse getAchievementLevelById(@PathVariable Integer userId) {
        // Call the service method and return the result
        return achievementLevelService.getAchievementLevelById(userId);
    }
}
