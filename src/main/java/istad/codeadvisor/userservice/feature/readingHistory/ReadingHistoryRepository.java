package istad.codeadvisor.userservice.feature.readingHistory;

import istad.codeadvisor.userservice.domain.ReadingHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReadingHistoryRepository extends MongoRepository<ReadingHistory, String> {
    Boolean existsByUserIdAndContentIdAndQuestionId(String userId, Integer contentId, Integer questionId);
    Optional<ReadingHistory> findByUserIdAndContentIdAndQuestionId(String userId, Integer contentId, Integer questionId);
    Optional<ReadingHistory> findById(String id);

}
