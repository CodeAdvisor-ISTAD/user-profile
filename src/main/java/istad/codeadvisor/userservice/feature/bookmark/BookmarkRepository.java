package istad.codeadvisor.userservice.feature.bookmark;

import istad.codeadvisor.userservice.domain.Bookmark;
import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends MongoRepository<Bookmark, String> {

    Boolean existsByAuthorUuidAndForumSlugAndIsBookmarkedTrueAndIsDeletedFalse(String authorUuid, String forumSlug);

    List<Bookmark> findAllByAuthorUuidAndIsDeletedFalse(String authorUuid);

    Page<Bookmark> findAllByAuthorUuidAndIsDeletedFalse(String authorUuid, @NotNull Pageable pageable);

    Bookmark findByForumSlug(String forumSlug);

    Bookmark findByForumSlugAndAuthorUuid(String forumSlug, String authorUuid);

    Bookmark findByContentSlugAndAuthorUuid(String contentSlug, String authorUuid);

}
