package istad.codeadvisor.userservice.config.kafka.producer;

import istad.codeadvisor.userservice.config.kafka.BaseProducer;
import lombok.Data;

@Data
public class AnswerQuestionProducer implements BaseProducer {
    private String questionOwnerUuid;
    private String answerOwnerUuid;
    private String description;
    private String forumSlug;
}
