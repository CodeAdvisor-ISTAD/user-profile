package istad.codeadvisor.userservice.config.kafka.producer;

import istad.codeadvisor.userservice.config.kafka.BaseProducer;
import lombok.Data;

@Data
public class ForumProducer implements BaseProducer {
    private String userId;
    private Integer askQuestionCount;
    private Integer answerQuestionCount;
}
