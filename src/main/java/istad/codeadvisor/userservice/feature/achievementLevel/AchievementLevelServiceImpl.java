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

    // Update the user's achievement level based on the content service data
    @Override
    public void updateFromContentService(Integer userId, Integer shareContentTotal, Integer commentTotal, Integer likeTotal) {
        // Retrieve the user's achievement data from the repository

        AchievementLevel achievement = achievementLevelRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
//
        achievementLevelRepository.save(achievement);
    }

    // Update the user's achievement level based on the forum service data
    @Override
    public void updateFromForumService(Integer userId, Integer askQuestionTotal, Integer answerQuestionTotal) {
        // Retrieve the user's achievement data from the repository
        AchievementLevel achievement = achievementLevelRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        // Update the user's achievement data with the new forum service data
        achievement.setAsk_question_total(askQuestionTotal);
        achievement.setAnswer_question_total(answerQuestionTotal);



        // Save the updated achievement data to the repository
        achievementLevelRepository.save(achievement);
    }

    // Calculate the total score for the user based on the achievements
    @Override
    public int calculateTotalScore(AchievementLevel achievement) {
        int totalPoints = 0;

        totalPoints += (achievement.getComment_total() * 5)
//                + (achievement.getAnswer_question_total() * 25)
                + (achievement.getInteraction_total() * 2)
//                + (achievement.getAsk_question_total() * 10)
                + (achievement.getShare_content_total() * 15);

        return totalPoints;
    }

    // Determine the user's achievement level based on the total score
    @Override
    public String determineAchievementLevel(Integer totalPoints) {
        String currentLevel;
        if (totalPoints >= 5000) {
            return "Verified Expert";
        } else if (totalPoints >= 3500 && totalPoints < 4999) {
            return "Top Contributor";
        } else if (totalPoints >= 100) {
            return "Contributor";
        } else {
            return "Beginner";
        }
    }
    // Get the user's achievement level by user ID
    @Override
    public AchievementLevelResponse getAchievementLevelById(Integer userId) {
        // Retrieve the user's achievement data from the repository
        AchievementLevel achievement = achievementLevelRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        // Calculate the total score for the user
        int totalPoints = calculateTotalScore(achievement);

        // Determine the user's achievement level based on the score
        achievement.setCurrentLevel(determineAchievementLevel(totalPoints));

        // Return the response containing the user ID, total points, user level, and status
        return achievementMapper.toAchievement(achievement, totalPoints, achievement.getCurrentLevel());
    }
}
