package istad.codeadvisor.userservice.config.kafka.producer;

import istad.codeadvisor.userservice.config.kafka.BaseProducer;
import lombok.Data;

@Data
public class ReactionProducer implements BaseProducer {
    private String userId;
    private String contentId;
    private String type;
    private String reactionType;
    private String oldReactionType;// Add this field to match the payload
    private String ownerId;
    private String slug;
}
