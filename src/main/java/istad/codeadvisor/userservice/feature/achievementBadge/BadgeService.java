package istad.codeadvisor.userservice.feature.achievementBadge;

import istad.codeadvisor.userservice.domain.AchievementBadge;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeCreateRequest;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeResponse;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeUpdateRequest;

import java.util.List;

public interface BadgeService {
    // create badge
    BadgeResponse createBadge(BadgeCreateRequest badgeCreateRequest);
    // get all badges
    List<AchievementBadge> getAllBadges();
    // get badge
    BadgeResponse getBadgeByName(String badgeName);
    // get badge by user id
    BadgeResponse getBadgeByUserId(String userId);
    // update badge
    BadgeResponse updateBadgeByName(String badgeName, BadgeUpdateRequest badgeUpdateRequest);
    // is public
    void isPublicBadge(String badgeName);
    // is unpublished
    void isUnpublishedBadge(String badgeName);
    // disable
    void disableBadge(String badgeName);
    // enable
    void enableBadge(String badgeName);
    // delete
    void deleteBadge(String badgeName);
}
