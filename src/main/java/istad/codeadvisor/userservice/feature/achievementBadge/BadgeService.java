package istad.codeadvisor.userservice.feature.achievementBadge;

import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeCreateRequest;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeResponse;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeUpdateRequest;

public interface BadgeService {
    // create badge
    BadgeResponse createBadge(BadgeCreateRequest badgeCreateRequest);
    // get badge
    BadgeResponse getBadgeByName(String badgeName);
    // update badge
    BadgeResponse updateBadgeByName(String badgeName, BadgeUpdateRequest badgeUpdateRequest);
    // is publish
    // disable
    void disableBadge(String badgeName);
    // delete
    void deleteBadge(String badgeName);
}
