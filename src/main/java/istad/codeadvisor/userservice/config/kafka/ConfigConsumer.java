package istad.codeadvisor.userservice.config.kafka;

import istad.codeadvisor.userservice.config.kafka.producer.CommentProducer;
import istad.codeadvisor.userservice.config.kafka.producer.ContentProducer;
import istad.codeadvisor.userservice.config.kafka.producer.ForumProducer;
import istad.codeadvisor.userservice.config.kafka.producer.ReactionProducer;
import istad.codeadvisor.userservice.feature.achievementLevel.AchievementLevelServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConfigConsumer implements BaseProducer {
    private final AchievementLevelServiceImpl achievementLevelServiceImpl;

    @KafkaListener(topics = "community-topic-interaction", groupId = "user-service")
    public void handleCommunityInteraction(ReactionProducer reactionProducer) {
        try {
            achievementLevelServiceImpl.updateFromCommunityService(
                    reactionProducer.getUserId(),
                    reactionProducer.getContentId(),
                    reactionProducer.getReactionType()
            );
            log.info("Successfully processed ReactionProducer message: {}", reactionProducer);
        } catch (Exception e) {
            log.error("Error processing ReactionProducer message: {}", reactionProducer, e);
        }
    }

    @KafkaListener(topics = "comment-created-events-topic", groupId = "user-service")
    public void handleCommentEvents(CommentProducer commentProducer) {
        try {
            achievementLevelServiceImpl.updateCommentProducer(
                    commentProducer.getUserId(),
                    commentProducer.getContentId(),
                    commentProducer.getType()
            );
            log.info("Successfully processed CommentProducer message: {}", commentProducer);
        } catch (Exception e) {
            log.error("Error processing CommentProducer message: {}", commentProducer, e);
        }
    }

    @KafkaListener(topics = "forum-topic-questions", groupId = "user-service")
    public void handleForumQuestions(ForumProducer forumProducer) {
        try {
            achievementLevelServiceImpl.updateFromForumService(
                    forumProducer.getUserId(),
                    forumProducer.getAskQuestionCount(),
                    forumProducer.getAnswerQuestionCount()
            );
            log.info("Successfully processed ForumProducer message: {}", forumProducer);
        } catch (Exception e) {
            log.error("Error processing ForumProducer message: {}", forumProducer, e);
        }
    }

    @KafkaListener(topics = "content-topic-answers", groupId = "user-service")
    public void handleForumAnswers(ContentProducer contentProducer) {
        try {
            achievementLevelServiceImpl.updateContentProducer(
                    contentProducer.getUserId(),
                    contentProducer.getContentId(),
                    contentProducer.getType()
            );
            log.info("Successfully processed ForumProducer message: {}", contentProducer);
        } catch (Exception e) {
            log.error("Error processing ForumProducer message: {}", contentProducer, e);
        }
    }
}
