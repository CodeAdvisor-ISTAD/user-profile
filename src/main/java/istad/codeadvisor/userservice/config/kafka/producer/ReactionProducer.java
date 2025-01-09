package istad.codeadvisor.userservice.config.kafka.producer;

import istad.codeadvisor.userservice.config.kafka.BaseProducer;
import lombok.Data;

@Data
public class ReactionProducer implements BaseProducer {
    private String contentId;
    private String type;
    private String userId;
    private String reactionType;
}
