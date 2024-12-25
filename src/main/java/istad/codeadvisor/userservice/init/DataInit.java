package istad.codeadvisor.userservice.init;

import istad.codeadvisor.userservice.domain.AchievementLevel;
import istad.codeadvisor.userservice.feature.achievementLevel.AchievementLevelRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {
    private final AchievementLevelRepository achievementLevelRepository;

    @PostConstruct
    public void init() {
        if (achievementLevelRepository.count() == 0) {
            AchievementLevel achievement1 = AchievementLevel.builder()
                    .userId(123)
//                    .comment_total(20)
//                    .answer_question_total(10)
//                    .interaction_total(50)
//                    .ask_question_total(5)
//                    .share_content_total(10)
                    .build();

            AchievementLevel achievement2 = AchievementLevel.builder()
                    .userId(2)
//                    .comment_total(4)
//                    .answer_question_total(1)
//                    .interaction_total(25)
//                    .ask_question_total(15)
//                    .share_content_total(2) // No chance of accidentally referencing achievement1 here
                    .build();

            achievementLevelRepository.save(achievement1);
            achievementLevelRepository.save(achievement2);

        }
    }
}
