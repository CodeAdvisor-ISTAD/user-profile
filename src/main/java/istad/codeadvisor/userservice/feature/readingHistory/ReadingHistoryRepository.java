package istad.codeadvisor.userservice.feature.readingHistory;

import istad.codeadvisor.userservice.domain.ReadingHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReadingHistoryRepository extends MongoRepository<ReadingHistory, String> {
    Boolean existsByUserIdAndContentIdAndQuestionId(Integer userId, Integer contentId, Integer questionId);
    Optional<ReadingHistory> findByUserIdAndContentIdAndQuestionId(Integer userId, Integer contentId, Integer questionId);

}
