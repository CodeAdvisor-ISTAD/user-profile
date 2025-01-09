//package istad.codeadvisor.userservice.init;
//
//import istad.codeadvisor.userservice.domain.AchievementLevel;
//import istad.codeadvisor.userservice.feature.achievementLevel.AchievementLevelRepository;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class DataInit {
//    private final AchievementLevelRepository achievementLevelRepository;
//
//    @PostConstruct
//    public void init() {
//        if (achievementLevelRepository.count() == 0) {
//            AchievementLevel achievement1 = AchievementLevel.builder()
//                    .userId("1")
//                    .comment_total(0)
//                    .answer_question_total(0)
//                    .interaction_total(0)
//                    .ask_question_total(0)
//                    .share_content_total(0)
//                    .build();
//
//            AchievementLevel achievement2 = AchievementLevel.builder()
//                    .userId("2")
//                    .comment_total(0)
//                    .answer_question_total(0)
//                    .interaction_total(0)
//                    .ask_question_total(0)
//                    .share_content_total(0) // No chance of accidentally referencing achievement1 here
//                    .build();
//
//            AchievementLevel achievement3 = AchievementLevel.builder()
//                    .userId("6770ed8da987096de060a532")
//                    .comment_total(0)
//                    .answer_question_total(0)
//                    .interaction_total(0)
//                    .ask_question_total(0)
//                    .share_content_total(0) // No chance of accidentally referencing achievement1 here
//                    .build();
//
//            achievementLevelRepository.save(achievement1);
//            achievementLevelRepository.save(achievement2);
//            achievementLevelRepository.save(achievement3);
//
//        }
//    }
//}
