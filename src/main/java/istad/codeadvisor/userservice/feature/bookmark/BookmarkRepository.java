package istad.codeadvisor.userservice.feature.bookmark;

import istad.codeadvisor.userservice.domain.Bookmark;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookmarkRepository extends MongoRepository<Bookmark, String> {
//    boolean existsByUserIdAndForumIdOrContentId(Integer userId, Integer forumId, Integer contentId);
    Optional<Bookmark> findByUserIdAndForumIdAndContentId(String userId, String forumId, String contentId, String s);
    Optional<Bookmark> findByUserId(String userId);
    Optional<Bookmark> findByAuthorUuid(String authorUuid);

}
