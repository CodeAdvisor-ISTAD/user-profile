package istad.codeadvisor.userservice.config.kafka;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import istad.codeadvisor.userservice.config.kafka.producer.*;

@JsonSubTypes({
        @JsonSubTypes.Type(value = ReactionProducer.class, name = "reaction"),
        @JsonSubTypes.Type(value = CommentProducer.class, name = "comment"),
        @JsonSubTypes.Type(value = AnswerQuestionProducer.class, name = "answer"),
        @JsonSubTypes.Type(value = AskQuestionProducer.class, name = "question"),
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