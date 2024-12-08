package istad.codeadvisor.userservice.feature.achievementBadge.dto;

import lombok.Builder;

@Builder
public record BadgeCreateRequest(
        String badgeName,
        String badgeImage
) {
}
