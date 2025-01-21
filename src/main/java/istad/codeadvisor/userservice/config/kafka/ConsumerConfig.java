package istad.codeadvisor.userservice.config.kafka;

import istad.codeadvisor.userservice.config.kafka.producer.*;
import istad.codeadvisor.userservice.feature.achievementLevel.AchievementLevelServiceImpl;
import istad.codeadvisor.userservice.feature.userprofile.UserProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerConfig implements BaseProducer {
    private final AchievementLevelServiceImpl achievementLevelServiceImpl;
    private final UserProfileServiceImpl userProfileServiceImpl;

    // Community interaction consumer
//    @KafkaListener(topics = "content-reacted-events-topic", groupId = "user-service")
    public void handleCommunityInteraction(ReactionProducer reactionProducer) {
        try {
            achievementLevelServiceImpl.updateFromReactionProducer(
                    reactionProducer.getContentId(),
                    reactionProducer.getType(),
                    reactionProducer.getUserId(),
                    reactionProducer.getReactionType()
            );
            log.info("Successfully processed ReactionProducer message: {}", reactionProducer);
        } catch (Exception e) {
            log.error("Error processing ReactionProducer message: {}", reactionProducer, e);
        }
    }

    // Comment consumer
//    @KafkaListener(topics = "comment-created-events-topic", groupId = "user-service")
    public void handleCommentEvents(CommentProducer commentProducer) {
        try {
            achievementLevelServiceImpl.updateCommentProducer(
                    commentProducer.getUserId(),
                    commentProducer.getContentId(),
                    commentProducer.getBody()
            );
            log.info("Successfully processed CommentProducer message: {}", commentProducer);
        } catch (Exception e) {
            log.error("Error processing CommentProducer message: {}", commentProducer, e);
        }
    }

    // create content consumer
    @KafkaListener(topics = "content-created-events-topic", groupId = "content-service")
    public void handleForumAnswers(ContentProducer contentProducer) {
        try {
            achievementLevelServiceImpl.createContentProducer(
                    contentProducer.getId(),
                    contentProducer.getTitle(),
                    contentProducer.getAuthorUuid(),
//                    contentProducer.getSlug(),
//                    contentProducer.getContent(),
//                    contentProducer.getThumbnail(),
                    contentProducer.getKeyword()

            );
            log.info("Successfully processed ForumProducer message: {}", contentProducer);
        } catch (Exception e) {
            log.error("Error processing ForumProducer message: {}", contentProducer, e);
        }
    }

    // UserIdentity consumer
    @KafkaListener(topics = "user-created-events-topic", groupId = "user-service")
    public void handleUserIdentity(UserIdentityProducer userIdentityProducer) {
        try {
            userProfileServiceImpl.updateUserProfileFromIdentity(
                    userIdentityProducer.getUuid(),
                    userIdentityProducer.getUsername(),
                    userIdentityProducer.getEmail(),
                    userIdentityProducer.getFullName(),
                    userIdentityProducer.getProfileImage()
            );
            log.info("Successfully processed UserIdentityProducer message: {}", userIdentityProducer);
        } catch (Exception e) {
            log.error("Error processing UserIdentityProducer message: {}", userIdentityProducer, e);
        }
    }
}