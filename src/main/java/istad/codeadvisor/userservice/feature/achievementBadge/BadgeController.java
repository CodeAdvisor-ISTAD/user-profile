package istad.codeadvisor.userservice.feature.achievementBadge;

import istad.codeadvisor.userservice.domain.AchievementBadge;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeCreateRequest;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeResponse;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/achievement_badges")
public class BadgeController {
    private final BadgeService badgeService;
    // create
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    BadgeResponse createBadge(@RequestBody BadgeCreateRequest badgeCreateRequest) {
        return badgeService.createBadge(badgeCreateRequest);
    }
    // get all badges
    @GetMapping
    List<AchievementBadge> getAllBadges() {
        return badgeService.getAllBadges();
    }
    // get badge
    @GetMapping("/{badgeName}")
    BadgeResponse getBadge(@Valid @PathVariable String badgeName) {
        return badgeService.getBadgeByName(badgeName);
    }
    // get badge by user id
    @GetMapping("user/{userId}")
    BadgeResponse getBadgeByUserId(@Valid @PathVariable String userId) {
        return badgeService.getBadgeByUserId(userId);
    }
    // update badge
    @PatchMapping("/{badgeName}")
    BadgeResponse updateBadge(@Valid @PathVariable String badgeName, @RequestBody BadgeUpdateRequest badgeUpdateRequest) {
        return badgeService.updateBadgeByName(badgeName, badgeUpdateRequest);
    }
    // disable badge
    @PatchMapping("/{badgeName}/is_deleted")
    void disableBadgeName(@Valid @PathVariable("badgeName") String badgeName) {
        badgeService.disableBadge(badgeName);
    }
    // enable badge
    @PatchMapping("/{badgeName}/is_enabled")
    void enableBadgeName(@Valid @PathVariable("badgeName") String badgeName) {
        badgeService.enableBadge(badgeName);
    }
    // is published badge
    @PatchMapping("/{badgeName}/is_published")
    void isPublicBadge(@Valid @PathVariable("badgeName") String badgeName) {
        badgeService.isPublicBadge(badgeName);
    }
    // is unpublished badge
    @PatchMapping("/{badgeName}/is_unpublished")
    void isUnpublishedBadge(@Valid @PathVariable("badgeName") String badgeName) {
        badgeService.isUnpublishedBadge(badgeName);
    }
    // delete
    @DeleteMapping("/{badgeName}")
    void deleteBadge(@Valid @PathVariable String badgeName) {
        badgeService.deleteBadge(badgeName);
    }
}
