package istad.codeadvisor.userservice.feature.bookmark;

import istad.codeadvisor.userservice.domain.Bookmark;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookmarkRepository extends MongoRepository<Bookmark, String> {
//    boolean existsByUserIdAndForumIdOrContentId(Integer userId, Integer forumId, Integer contentId);
    Optional<Bookmark> findByUserIdAndForumIdAndContentId(String userId, Integer forumId, Integer contentId);
    Optional<Bookmark> findById(String id);

}
