package istad.codeadvisor.userservice.feature.achievementBadge;

import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeCreateRequest;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeResponse;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    // get badge
    @GetMapping("/{badgeName}")
    BadgeResponse getBadge(@Valid @PathVariable String badgeName) {
        return badgeService.getBadgeByName(badgeName);
    }
    // update badge
    @PatchMapping("/{badgeName}")
    BadgeResponse updateBadge(@Valid @PathVariable String badgeName, @RequestBody BadgeUpdateRequest badgeUpdateRequest) {
        return badgeService.updateBadgeByName(badgeName, badgeUpdateRequest);
    }
    // disable badge
    @PatchMapping("/{badgeName}/disable")
    void disableBadgeName(@Valid @PathVariable("badgeName") String badgeName) {
        badgeService.disableBadge(badgeName);
    }
    // delete
    @DeleteMapping("/{badgeName}")
    void deleteBadge(@Valid @PathVariable String badgeName) {
        badgeService.deleteBadge(badgeName);
    }
}
