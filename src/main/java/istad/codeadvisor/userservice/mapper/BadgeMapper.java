package istad.codeadvisor.userservice.mapper;

import istad.codeadvisor.userservice.domain.AchievementBadge;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeCreateRequest;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeResponse;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface BadgeMapper {
    //create
    AchievementBadge fromBadgeCreateRequest(BadgeCreateRequest badgeCreateRequest);
    // get all badges
    List<AchievementBadge> toAchievementBadgeResponseList(List<AchievementBadge> achievementBadges);
    // get badge
    BadgeResponse toAchievementBadgeResponse(AchievementBadge achievementBadge);
    //update badge
    void fromBadgeUpdateRequest(BadgeUpdateRequest badgeUpdateRequest, @MappingTarget AchievementBadge achievementBadge);

}
