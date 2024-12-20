package istad.codeadvisor.userservice.feature.readingHistory;

import istad.codeadvisor.userservice.domain.ReadingHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReadingHistoryRepository extends MongoRepository<ReadingHistory, String> {
//    <Optional> ReadingHistory findByUserIdAndQuestionIdAndContentId(String userId, Integer questionId, Integer contentId);
}
