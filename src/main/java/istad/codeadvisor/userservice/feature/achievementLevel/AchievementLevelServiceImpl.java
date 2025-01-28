package istad.codeadvisor.userservice.feature.achievementLevel;

import istad.codeadvisor.userservice.domain.AchievementLevel;
import istad.codeadvisor.userservice.feature.achievementLevel.dto.AchievementLevelResponse;
import istad.codeadvisor.userservice.mapper.AchievementMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
@Slf4j
public class AchievementLevelServiceImpl implements AchievementLevelService {

    private final AchievementLevelRepository achievementLevelRepository;
    private final AchievementMapper achievementMapper;

    @Override
    public void updateFromReactionProducer(String contentId, String type, String userId, String reactionType) {
        // Retrieve the user's achievement data from the repository
        AchievementLevel achievement = achievementLevelRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        // Update the user's achievement data based on the community service data
        switch (reactionType) {
            case "like":
            case "fire":
            case "love":
                achievement.setInteraction_total(achievement.getInteraction_total() + 1);
                break;
            case "comment":
                achievement.setComment_total(achievement.getComment_total() + 1);
                break;
            case "share":
                achievement.setShare_content_total(achievement.getShare_content_total() + 1);
                break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid reaction type!");
        }

        // Save the updated achievement data to the repository
        achievementLevelRepository.save(achievement);
    }

    // Update the user's achievement level based on the comment service data
    @Override
    public void updateCommentProducer(String userId, String contentId, String body) {
        // Retrieve the user's achievement data from the repository
        AchievementLevel achievement = achievementLevelRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        achievement.setComment_total(achievement.getComment_total() + 1);
        // Save the updated achievement data to the repository
        achievementLevelRepository.save(achievement);
    }

    @Override
    public void createForumProducer(String authorUuid, String uuid , String slug, String description) {
        // Retrieve the user's achievement data from the repository
        AchievementLevel achievement = achievementLevelRepository.findByAuthorUuid(authorUuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        achievement.setAsk_question_total(achievement.getAsk_question_total() + 1);
        // Save the updated achievement data to the repository
        achievementLevelRepository.save(achievement);
    }

    @Override
    public void answerForumProducer(String questionOwnerUuid, String answerOwnerUuid, String description, String forumSlug) {
        // Retrieve the user's achievement data from the repository
        AchievementLevel achievement = achievementLevelRepository.findByAuthorUuid(questionOwnerUuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        achievement.setAnswer_question_total(achievement.getAnswer_question_total() + 1);
        // Save the updated achievement data to the repository
        achievementLevelRepository.save(achievement);
    }

    // Update the user's achievement level based on the content service data
    @Override
    public void createContentProducer(
            String id,
            String title,
            String authorUuid,
//            String slug,
//            String content,
//            String thumbnail,
            String keyword) {
        // Retrieve the user's achievement data from the repository
        AchievementLevel achievement = achievementLevelRepository.findByAuthorUuid(authorUuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        achievement.setShare_content_total(achievement.getShare_content_total() + 1);
        // Save the updated achievement data to the repository
        achievementLevelRepository.save(achievement);
    }

    // Calculate the total score for the user based on the achievements
    @Override
    public int calculateTotalScore(AchievementLevel achievement) {
        if (achievement == null) {
            return 0; // Return 0 if the entire object is null
        }
        // Calculate total points with null-safe checks
        int totalPoints = 0;

        totalPoints += (achievement.getComment_total() != null ? achievement.getComment_total() : 0) * 5;
        totalPoints += (achievement.getAnswer_question_total() != null ? achievement.getAnswer_question_total() : 0) * 25;
        totalPoints += (achievement.getInteraction_total() != null ? achievement.getInteraction_total() : 0) * 2;
        totalPoints += (achievement.getAsk_question_total() != null ? achievement.getAsk_question_total() : 0) * 10;
        totalPoints += (achievement.getShare_content_total() != null ? achievement.getShare_content_total() : 0) * 15;

        return totalPoints;
    }

    // Determine the user's achievement level based on the total score
    @Override
    public String determineAchievementLevel(Integer totalPoints) {
        if (totalPoints >= 5000) {
            return "Verified Expert";
        } else if (totalPoints >= 4000 && totalPoints < 4999) {
            return "Top Contributor";
        } else if (totalPoints >= 3000 && totalPoints < 3999) {
            return "Mentor";
        } else if (totalPoints >= 2000 && totalPoints < 2999) {
            return "Expert";
        } else if (totalPoints >= 1000 && totalPoints < 1999) {
            return "Senior";
        } else if (totalPoints >= 200 && totalPoints < 999) {
            return "Contributor";
        } else {
            return "Learner";
        }
    }
    // Get the user's achievement level by user ID
    @Override
    public AchievementLevelResponse getAchievementLevelByUserId(String userId) {
        // Retrieve the user's achievement data from the repository
//        AchievementLevel achievement = achievementLevelRepository.findByIdAndIsPublish(userId, true)
        AchievementLevel achievement = achievementLevelRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        // Calculate the total score for the user
        int totalPoints = calculateTotalScore(achievement);

        // Determine the user's achievement level based on the score
        achievement.setCurrentLevel(determineAchievementLevel(totalPoints));

        // Return the response containing the user ID, total points, user level, and status
        return achievementMapper.toAchievement(achievement, totalPoints, achievement.getCurrentLevel());
    }

    // Disable the user's achievement level by username
    @Override
    public void disableAchievementLevel(String username) {
        AchievementLevel achievement = achievementLevelRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        achievement.setIsDeleted(true);
        achievementLevelRepository.save(achievement);
    }

    // Enable the user's achievement level by username
    @Override
    public void enableAchievementLevel(String username) {
        AchievementLevel achievement = achievementLevelRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        achievement.setIsDeleted(false);
        achievementLevelRepository.save(achievement);
    }

    // isPublic
    @Override
    public void isPublic(String username) {
        AchievementLevel achievement = achievementLevelRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        achievement.setIsPublish(true);
        achievementLevelRepository.save(achievement);
    }

    // isUnpublished
    @Override
    public void isUnpublished(String username) {
        AchievementLevel achievement = achievementLevelRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        achievement.setIsPublish(false);
        achievementLevelRepository.save(achievement);
    }
}
