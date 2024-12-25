package istad.codeadvisor.userservice.config.kafka.producer;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ForumProducer {
    private Integer userId;
    private Integer askQuestionCount;
    private Integer answerQuestionCount;
    private LocalDateTime lastUpdated;
}
