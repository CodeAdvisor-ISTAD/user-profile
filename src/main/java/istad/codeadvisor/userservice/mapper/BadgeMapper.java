package istad.codeadvisor.userservice.mapper;

import istad.codeadvisor.userservice.domain.AchievementBadge;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeCreateRequest;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeResponse;
import istad.codeadvisor.userservice.feature.achievementBadge.dto.BadgeUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "Spring")
public interface BadgeMapper {
    //create
    AchievementBadge fromBadgeCreateRequest(BadgeCreateRequest badgeCreateRequest);
    // get badge
    BadgeResponse toAchievementBadge(AchievementBadge achievementBadge);
    //update badge
    void fromBadgeUpdateRequest(BadgeUpdateRequest badgeUpdateRequest, @MappingTarget AchievementBadge achievementBadge);

}
