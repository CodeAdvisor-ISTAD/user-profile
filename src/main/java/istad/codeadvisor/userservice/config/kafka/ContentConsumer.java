package istad.codeadvisor.userservice.config.kafka;

import istad.codeadvisor.userservice.feature.achievementLevel.AchievementLevelServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import istad.codeadvisor.userservice.config.kafka.producer.ContentProducer;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentConsumer {
    private final AchievementLevelServiceImpl achievementLevelServiceImpl;

    @KafkaListener(topics = "content-service-topic", groupId = "user-service")
    public void consumeContentService(ContentProducer contentProducer) {
            achievementLevelServiceImpl.updateFromContentService(
                    contentProducer.getUserId(),
                    contentProducer.getShareCount(),
                    contentProducer.getCommentCount(),
                    contentProducer.getLikeCount()
            );
            log.info("Successfully processed message: {}", contentProducer);
    }
}
