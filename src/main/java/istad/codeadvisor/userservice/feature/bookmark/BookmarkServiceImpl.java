package istad.codeadvisor.userservice.feature.bookmark;

import istad.codeadvisor.userservice.domain.Bookmark;
import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkAddRequest;
import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkResponse;
import istad.codeadvisor.userservice.mapper.BookmarkMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;

    // Add a forum or content to the bookmark
    @Override
    public BookmarkResponse addBookmark(BookmarkAddRequest bookmarkAddRequest, String authorUuid) {
        // Check if the forum or content is already bookmarked by the user
        Bookmark existingBookmark = bookmarkRepository.findByForumSlugAndAuthorUuid(
                bookmarkAddRequest.forumSlug(),
                authorUuid
        );

        Bookmark existingContentBookmark = bookmarkRepository.findByContentSlugAndAuthorUuid(
                bookmarkAddRequest.contentSlug(),
                authorUuid
        );

        // If the bookmark already exists and is active, do not allow adding it again
        if (existingBookmark != null && existingBookmark.getIsBookmarked()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This forum is already bookmarked by the user.");
        }

        if (existingContentBookmark != null && existingContentBookmark.getIsBookmarked()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This content is already bookmarked by the user.");
        }

        // If the bookmark exists but was removed (isBookmarked = false), toggle it back to true
        if (existingBookmark != null) {
            existingBookmark.setIsBookmarked(true);
            existingBookmark.setIsDeleted(false); // Mark as active
            bookmarkRepository.save(existingBookmark);
            return bookmarkMapper.toBookmark(existingBookmark);
        }

        if (existingContentBookmark != null) {
            existingContentBookmark.setIsBookmarked(true);
            existingContentBookmark.setIsDeleted(false); // Mark as active
            bookmarkRepository.save(existingContentBookmark);
            return bookmarkMapper.toBookmark(existingContentBookmark);
        }

        // If no existing bookmark is found, create a new one
        Bookmark newBookmark = bookmarkMapper.fromBookmarkAddRequest(bookmarkAddRequest);
        newBookmark.setAuthorUuid(authorUuid);
        newBookmark.setCreatedAt(LocalDateTime.now());
        newBookmark.setIsDeleted(false); // New bookmarks are active
        newBookmark.setIsBookmarked(true); // New bookmarks are bookmarked

        // Save the new bookmark
        bookmarkRepository.save(newBookmark);

        // Return the bookmark response
        return bookmarkMapper.toBookmark(newBookmark);
    }

    @Override
    public Page<BookmarkResponse> findAllBookmark(String authorUuid, int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Bookmark> bookmarks = bookmarkRepository.findAllByAuthorUuidAndIsDeletedFalse(authorUuid, pageable);

        return bookmarks.map(bookmarkMapper::toBookmark);
    }


    @Override
    public BookmarkResponse unBookmarkForum(String forumSlug, String authorUuid) {

        Bookmark bookmark = bookmarkRepository.findByForumSlug(forumSlug);
        if (bookmark == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Do not have (forum/content) in bookmark !");
        }

        bookmark.setIsBookmarked(false);
        bookmarkRepository.save(bookmark);


        return bookmarkMapper.toBookmark(bookmark);
    }

    public Boolean checkBookmarkStatus(String authorUuid, String forumSlug) {
        return bookmarkRepository.existsByAuthorUuidAndForumSlugAndIsBookmarkedTrueAndIsDeletedFalse(
                authorUuid,
                forumSlug
        );
    }


}
