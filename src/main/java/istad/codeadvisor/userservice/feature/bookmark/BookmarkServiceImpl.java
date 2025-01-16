package istad.codeadvisor.userservice.feature.bookmark;

import istad.codeadvisor.userservice.domain.Bookmark;
import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkAddRequest;
import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkResponse;
import istad.codeadvisor.userservice.mapper.BookmarkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;

    // Add a forum or content to the bookmark
    @Override
    public BookmarkResponse addBookmark(BookmarkAddRequest bookmarkAddRequest) {
        // Check if the user has already bookmarked the same forum or content
        Optional<Bookmark> existingBookmark = bookmarkRepository.findByUserIdAndForumIdAndContentId(
                bookmarkAddRequest.userId(),
                bookmarkAddRequest.authorUuid(),
                bookmarkAddRequest.forumId(),
                bookmarkAddRequest.contentId()
        );

        if (existingBookmark.isPresent()) {
            // If the bookmark exists, toggle the isDeleted status
            Bookmark bookmark = existingBookmark.get();
            bookmark.setIsDeleted(!bookmark.getIsDeleted()); // Toggle the status
            bookmark.setBookmarkedAt(LocalDateTime.now());
            bookmarkRepository.save(bookmark);
            return bookmarkMapper.toBookmark(bookmark); // Return the updated bookmark
        } else {
            // If no bookmark exists for the user, create a new one
            Bookmark newBookmark = bookmarkMapper.fromBookmarkAddRequest(bookmarkAddRequest);
//            newBookmark.setUserId(bookmarkAddRequest.userId); // Set the userId for the new bookmark
            newBookmark.setBookmarkedAt(LocalDateTime.now());
            newBookmark.setIsDeleted(false); // New bookmarks are active
            bookmarkRepository.save(newBookmark);
            return bookmarkMapper.toBookmark(newBookmark); // Return the newly created bookmark
        }
    }

    // Get all bookmarks for a user
    @Override
    public List<BookmarkResponse> getBookmarks(String authorUuid) {
        List<Bookmark> bookmarks = bookmarkRepository.findAll();
//        List<Bookmark> bookmarks = Collections.singletonList(bookmarkRepository.findByAuthorUuid(authorUuid)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        "Do not have any bookmark !")));
//        return bookmarkMapper.toBookmarkList(bookmarks);
        return bookmarkMapper.toBookmarkList(bookmarks);
    }

    // Remove a forum or content from the bookmark
    @Override
    public void removeBookmark(String id) {

        Bookmark bookmark = bookmarkRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Do not have (forum/content) in bookmark !"));
        bookmark.setIsDeleted(true);
        bookmarkRepository.save(bookmark);
    }

}
