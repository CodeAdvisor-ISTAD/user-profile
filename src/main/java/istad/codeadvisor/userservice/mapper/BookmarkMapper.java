package istad.codeadvisor.userservice.mapper;

import istad.codeadvisor.userservice.domain.Bookmark;
import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkAddRequest;
import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface BookmarkMapper {
    // add forum or content to bookmark
    Bookmark fromBookmarkAddRequest(BookmarkAddRequest bookmarkAddRequest);
    // get each bookmarks for a user
    BookmarkResponse toBookmark(Bookmark bookmark);
    // get all bookmarks for a user
    List<BookmarkResponse> toBookmarkList(List<Bookmark> bookmarks);
}
