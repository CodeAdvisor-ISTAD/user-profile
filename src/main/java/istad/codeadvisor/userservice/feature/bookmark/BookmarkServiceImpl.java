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
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;

    // Add a forum or content to the bookmark
    @Override
    public BookmarkResponse addBookmark(BookmarkAddRequest bookmarkAddRequest, String authorUuid) {
        // Check if the forum is already bookmarked by the user
        Bookmark existingBookmark = bookmarkRepository.findByForumSlugAndAuthorUuid(
                bookmarkAddRequest.forumSlug(),
                authorUuid
        );

        Bookmark existingContentBookmark = bookmarkRepository.findByContentSlugAndAuthorUuid(
                bookmarkAddRequest.contentSlug(),
                authorUuid
        );


        if (existingBookmark != null) {
            // Toggle the bookmark status
            existingBookmark.setIsBookmarked(!existingBookmark.getIsBookmarked());
            existingContentBookmark.setIsBookmarked(!existingContentBookmark.getIsBookmarked());
        } else {
            // Create a new bookmark
            existingBookmark = bookmarkMapper.fromBookmarkAddRequest(bookmarkAddRequest);
            existingBookmark.setAuthorUuid(authorUuid);
            existingBookmark.setCreatedAt(LocalDateTime.now());
            existingBookmark.setIsDeleted(false); // New bookmarks are active
            existingBookmark.setIsBookmarked(true); // New bookmarks are bookmarked
        }

        // Save the updated or new bookmark
        bookmarkRepository.save(existingBookmark);

        // Return the bookmark response
        return bookmarkMapper.toBookmark(existingBookmark);
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
