package istad.codeadvisor.userservice.config.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import istad.codeadvisor.userservice.config.kafka.producer.*;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class BaseProducerDeserializer implements Deserializer<BaseProducer> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No configuration needed
    }

    @Override
    public BaseProducer deserialize(String topic, byte[] data) {
        try {
            if (data == null || data.length == 0) {
                return null; // Handle null payload gracefully
            }
            // Parse the JSON to determine the type
            JsonNode rootNode = objectMapper.readTree(data);
            if (rootNode.has("reactionType")) {
                return objectMapper.treeToValue(rootNode, ReactionProducer.class);
            } else if (rootNode.has("contentId") && rootNode.has("body")) {
                return objectMapper.treeToValue(rootNode, CommentProducer.class);
//            } else if (rootNode.has("askQuestionCount") && rootNode.has("answerQuestionCount")) {
//                return objectMapper.treeToValue(rootNode, ForumProducer.class);
            } else if (rootNode.has("authorUuid") && rootNode.has("keyword")) {
                return objectMapper.treeToValue(rootNode, ContentProducer.class);
            } else if (rootNode.has("username") && rootNode.has("email") && rootNode.has("fullName")) {
                return objectMapper.treeToValue(rootNode, UserIdentityProducer.class);
            }else if (rootNode.has("uuid") && rootNode.has("description") && rootNode.has("expectedAnswers")) {
                return objectMapper.treeToValue(rootNode, AskQuestionProducer.class);
            }else if (rootNode.has("answerOwnerUuid") && rootNode.has("questionOwnerUuid") && rootNode.has("forumSlug")) {
                return objectMapper.treeToValue(rootNode, AnswerQuestionProducer.class);
            } else {
                throw new IllegalArgumentException("Unknown message structure: " + new String(data));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize BaseProducer: " + new String(data), e);
        }
    }

    @Override
    public void close() {
        // No cleanup necessary
    }
}
