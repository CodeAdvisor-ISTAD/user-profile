package istad.codeadvisor.userservice.feature.readingHistory;

import istad.codeadvisor.userservice.domain.ReadingHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReadingHistoryRepository extends MongoRepository<ReadingHistory, String> {
    ReadingHistory findByForumSlug( String forumSlug);

    ReadingHistory findByContentSlug( String contentSlug);

    ReadingHistory findByForumSlugOrContentSlug( String forumSlug, String contentSlug);
    ReadingHistory findByForumSlugOrContentSlugAndAuthorUuid( String forumSlug, String contentSlug, String authorUuid);
}
