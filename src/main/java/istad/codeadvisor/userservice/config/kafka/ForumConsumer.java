//package istad.codeadvisor.userservice.config.kafka;
//
//import istad.codeadvisor.userservice.config.kafka.producer.ForumProducer;
//import istad.codeadvisor.userservice.feature.achievementLevel.AchievementLevelServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class ForumConsumer {
//    private final AchievementLevelServiceImpl achievementLevelServiceImpl;
//
//    @KafkaListener(topics = "forum-service-topic", groupId = "user-service")
//    public void consumeForumService(ForumProducer forumProducer) {
//        achievementLevelServiceImpl.updateFromForumService(
//                forumProducer.getUserId(),
//                forumProducer.getAskQuestionCount(),
//                forumProducer.getAnswerQuestionCount()
//        );
//    }
//}
