package istad.codeadvisor.userservice.config.kafka.producer;

import istad.codeadvisor.userservice.config.kafka.BaseProducer;
import lombok.Data;

@Data
public class AskQuestionProducer implements BaseProducer {
    private String uuid;
    private String slug;
    private String authorUuid;
    private String title;
    private String description;
    private String introduction;
    private String expectedAnswers;


}
