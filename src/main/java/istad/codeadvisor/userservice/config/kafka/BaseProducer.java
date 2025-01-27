package istad.codeadvisor.userservice.config.kafka;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import istad.codeadvisor.userservice.config.kafka.producer.CommentProducer;
import istad.codeadvisor.userservice.config.kafka.producer.ReactionProducer;
import istad.codeadvisor.userservice.config.kafka.producer.UserIdentityProducer;

@JsonSubTypes({
        @JsonSubTypes.Type(value = ReactionProducer.class, name = "reaction"),
        @JsonSubTypes.Type(value = CommentProducer.class, name = "comment"),
//        @JsonSubTypes.Type(value = ForumProducer.class, name = "forum"),
        @JsonSubTypes.Type(value = CommentProducer.class, name = "content"),
        @JsonSubTypes.Type(value = UserIdentityProducer.class, name = "identity")
})
public interface BaseProducer {
//    //TODO: Comment Producer data
//    @Data
//    class CommentProducer implements BaseProducer {
//        private String userId;
//        private String contentId;
//        private String type;
//
//    }
//    //Todo: Reaction Producer data
//    @Data
//    class ReactionProducer implements BaseProducer {
//        private String userId;
//        private String contentId;
//        private String reactionType;
//
//    }
//    //TODO: Forum Producer data
//    @Data
//    class ForumProducer {
//        private Integer userId;
//        private Integer askQuestionCount;
//        private Integer answerQuestionCount;
//    }
//
//    @Data
//    class ContentProducer {
//        private String userId;
//        private String contentId;
//        private String type;
//    }
}