package istad.codeadvisor.userservice.config.kafka.producer;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReactionProducer {
    private String userId;
    private String contentId;
    private String reactionType;

}
