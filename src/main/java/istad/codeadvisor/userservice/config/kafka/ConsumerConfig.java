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
    @KafkaListener(topics = "content-reacted-events-topic", groupId = "user-service")
    public void handleCommunityInteraction(ReactionProducer reactionProducer) {
        try {
            achievementLevelServiceImpl.updateFromReactionProducer(
                    reactionProducer.getUserId(),
                    reactionProducer.getContentId(),
                    reactionProducer.getType(),
                    reactionProducer.getReactionType()
            );
            log.info("Successfully processed ReactionProducer message: {}", reactionProducer);
        } catch (Exception e) {
            log.error("Error processing ReactionProducer message: {}", reactionProducer, e);
        }
    }

    // Comment consumer
    @KafkaListener(topics = "comment-created-events-topic", groupId = "user-service")
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

    // create Question consumer
    @KafkaListener(topics = "forum-created-events-topic", groupId = "question-service")
    public void handleAskQuestion(AskQuestionProducer askQuestionProducer) {
        try {
            achievementLevelServiceImpl.createForumProducer(
                    askQuestionProducer.getAuthorUuid(),
                    askQuestionProducer.getUuid(),
                    askQuestionProducer.getSlug(),

                    askQuestionProducer.getDescription()
            );
            log.info("Successfully processed AskQuestionProducer message: {}", askQuestionProducer);
        } catch (Exception e) {
            log.error("Error processing AskQuestionProducer message: {}", askQuestionProducer, e);
        }
    }

    // Answer question consumer
    @KafkaListener(topics = "forum-answer-created-events-topic", groupId = "question-service")
    public void handleAnswerQuestion(AnswerQuestionProducer answerQuestionProducer) {
        try {
            achievementLevelServiceImpl.answerForumProducer(
                    answerQuestionProducer.getQuestionOwnerUuid(),
                    answerQuestionProducer.getAnswerOwnerUuid(),
                    answerQuestionProducer.getDescription(),
                    answerQuestionProducer.getForumSlug()
            );
            log.info("Successfully processed AnswerQuestionProducer message: {}", answerQuestionProducer);
        } catch (Exception e) {
            log.error("Error processing AnswerQuestionProducer message: {}", answerQuestionProducer, e);
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