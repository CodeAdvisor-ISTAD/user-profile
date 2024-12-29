package istad.codeadvisor.userservice.additional;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentServiceImpl.class);
    private static final String REACTION_TOPIC = "community-topic-interaction";

    private final KafkaTemplate<String, Content> kafkaTemplate;

    @Override
    public void postNewContent(ContentCreate contentCreate) {
        Content content = Content.builder()
                .userId(contentCreate.userId())
                .contentId(contentCreate.contentId())
                .reactionType(contentCreate.reactionType())
                .build();

        try {
            kafkaTemplate.send(REACTION_TOPIC, content);
            LOGGER.info("Message sent to Kafka: {}", content);
        } catch (Exception e) {
            LOGGER.error("Error sending message to Kafka: {}", e.getMessage(), e);
        }
    }
}
