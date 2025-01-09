package istad.codeadvisor.userservice.feature.achievementLevel;

import istad.codeadvisor.userservice.domain.AchievementLevel;
import istad.codeadvisor.userservice.feature.achievementLevel.dto.AchievementLevelResponse;

import java.util.List;

public interface AchievementLevelService {

    String determineAchievementLevel(Integer totalPoints);

    // Get achievement level by user id
    AchievementLevelResponse getAchievementLevelById(String userId);

    // Disable achievement level by username
    void disableAchievementLevel(String username);

    // Enable achievement level by username
    void enableAchievementLevel(String username);

    // Calculate the total score for the user
    int calculateTotalScore(AchievementLevel achievement);

    // handle the update from the content service
//    void updateFromContentService(String userId, Integer shareContentTotal, Integer commentTotal, Integer likeTotal);
    // handle the update from the forum service
    void updateFromForumService(String userId, Integer askQuestionTotal, Integer answerQuestionTotal);
    // handle the update from the reaction service
    void updateFromReactionProducer(String contentId, String type, String userId, String reactionType);
    // handle the update from the comment service
    void updateCommentProducer(String userId, String contentId, String body);
    // handle the update from forum service
    void updateForumProducer(String userId, Integer askQuestionCount, Integer answerQuestionCount);
    // handle the update from the content service
    void updateContentProducer(
            String id,
            String title,
            String authorUuid,
//            String slug,
//            String content,
//            String thumbnail,
            String keyword
    );
    // isPublic
    void isPublic(String username);

    // isUnpublished
    void isUnpublished(String username);

}
