package istad.codeadvisor.userservice.config.kafka.producer;

import lombok.Data;

@Data
public class ContentProducer {
    private Integer userId;
    private Integer shareCount;
    private Integer commentCount;
    private Integer likeCount;
}
