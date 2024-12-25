package istad.codeadvisor.userservice.config.kafka.producer;

import lombok.Data;

@Data
public class CommentProducer {
    private String userId;
    private String contentId;

}
