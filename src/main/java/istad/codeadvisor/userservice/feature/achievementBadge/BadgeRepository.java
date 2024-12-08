package istad.codeadvisor.userservice.feature.achievementBadge;

import istad.codeadvisor.userservice.domain.AchievementBadge;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BadgeRepository extends MongoRepository<AchievementBadge, String> {
    Boolean existsByBadgeName(String badgeName);
    Optional<AchievementBadge> findByBadgeName(String badgeName);
}
