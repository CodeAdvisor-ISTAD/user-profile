package istad.codeadvisor.userservice.feature.bookmark;

import istad.codeadvisor.userservice.domain.Bookmark;
import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkAddRequest;
import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookmarkService {
    // add forum or content to bookmark
    BookmarkResponse addBookmark(BookmarkAddRequest bookmarkAddRequest, String authorUuid);

    // get all bookmarks for a user
    Page<BookmarkResponse> findAllBookmark(String authorUuid, int page, int size);

    BookmarkResponse unBookmarkForum(String forumSlug, String authorUuid);

    Boolean checkBookmarkStatus(String forumSlug, String authorUuid);
}
