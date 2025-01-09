package istad.codeadvisor.userservice.config.kafka.producer;

import istad.codeadvisor.userservice.config.kafka.BaseProducer;
import lombok.Data;

import java.util.List;

@Data
public class ContentProducer implements BaseProducer {
    private String id;
    private String title;
    private String authorUuid;
    private String slug;
    private String content;
    private String thumbnail;
    private String keyword;
    private List<String> tags;


}
//