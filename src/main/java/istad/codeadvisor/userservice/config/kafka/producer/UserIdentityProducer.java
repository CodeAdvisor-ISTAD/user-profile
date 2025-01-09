package istad.codeadvisor.userservice.config.kafka.producer;

import istad.codeadvisor.userservice.config.kafka.BaseProducer;
import lombok.Data;

@Data
public class UserIdentityProducer implements BaseProducer {
    private String uuid;
    private String username;
    private String email;
    private String profileImage;
    private String fullName;
}
