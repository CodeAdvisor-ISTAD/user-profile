package istad.codeadvisor.userservice.feature.achievementBadge.dto;

import lombok.Builder;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public record BadgeResponse(
        String id,  
        String userId,
        String badgeName,
        String badgeImage,
        LocalDateTime assignAt
) {
}
