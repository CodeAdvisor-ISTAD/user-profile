package istad.codeadvisor.userservice.feature.achievementLevel;

import istad.codeadvisor.userservice.domain.AchievementLevel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AchievementLevelRepository extends MongoRepository<AchievementLevel, String> {
    Optional<AchievementLevel> findByUserId(String userId);
    Optional<AchievementLevel> findByAuthorUuid(String authorUuid);
    Optional<AchievementLevel> findByUsername(String username);
    Optional<AchievementLevel> findByUserIdAndIsPublish(String userId, Boolean isPublish);
}
