package istad.codeadvisor.userservice.feature.bookmark;

import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkAddRequest;
import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkResponse;

import java.util.List;

public interface BookmarkService {
    // add forum or content to bookmark
    BookmarkResponse addBookmark(BookmarkAddRequest bookmarkAddRequest);

    // get all bookmarks for a user
    List<BookmarkResponse> getBookmarks();
    // remove forum or content from bookmark
    void removeBookmark(String id);

}
