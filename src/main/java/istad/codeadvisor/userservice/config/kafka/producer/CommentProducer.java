package istad.codeadvisor.userservice.config.kafka.producer;

import istad.codeadvisor.userservice.config.kafka.BaseProducer;
import lombok.Data;

@Data
public class CommentProducer implements BaseProducer {
    private String userId;
    private String contentId;
    private String type;
    private String ownerId;
    private String body; // Add this field to match the payload
    private String slug;
}
