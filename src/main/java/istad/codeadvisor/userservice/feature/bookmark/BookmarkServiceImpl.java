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
        // Check if the bookmark already exists
        Optional<Bookmark> existingBookmark = bookmarkRepository.findByUserIdAndForumIdOrContentId(
                bookmarkAddRequest.userId(),
                bookmarkAddRequest.forumId(),
                bookmarkAddRequest.contentId()
        );
        if (existingBookmark.isPresent()) {
            // Toggle the isDeleted status if the bookmark exists
            Bookmark bookmark = existingBookmark.get();
            bookmark.setIsDeleted(!bookmark.getIsDeleted()); // true -> false, false -> true
            bookmark.setBookmarkedAt(LocalDateTime.now());
            bookmarkRepository.save(bookmark);
            return bookmarkMapper.toBookmark(bookmark); // Return the updated bookmark
        } else {
            // Create a new bookmark if none exists
            Bookmark newBookmark = bookmarkMapper.fromBookmarkAddRequest(bookmarkAddRequest);
            newBookmark.setBookmarkedAt(LocalDateTime.now());
            newBookmark.setIsDeleted(false); // New bookmarks are active
            newBookmark.setBookmarkedAt(LocalDateTime.now());
            bookmarkRepository.save(newBookmark);
            return bookmarkMapper.toBookmark(newBookmark); // Return the newly created bookmark
        }
    }

    // Get all bookmarks for a user
    @Override
    public List<BookmarkResponse> getBookmarks() {
        List<Bookmark> bookmarks = bookmarkRepository.findAll();
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
