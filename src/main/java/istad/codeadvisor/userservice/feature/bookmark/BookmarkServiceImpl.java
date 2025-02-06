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
//    @Override
//    public BookmarkResponse addBookmark(BookmarkAddRequest bookmarkAddRequest, String authorUuid) {
//        // Handle forum bookmark if provided
//        if (bookmarkAddRequest.forumSlug() != null) {
//            Bookmark forumBookmark = bookmarkRepository.findByForumSlugAndAuthorUuid(bookmarkAddRequest.forumSlug(), authorUuid);
//            if (forumBookmark != null) {
//                if (forumBookmark.getIsBookmarked()) {
//                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This forum is already bookmarked by the user.");
//                }
//                forumBookmark.setIsBookmarked(true);
//                forumBookmark.setIsDeleted(false);
//                bookmarkRepository.save(forumBookmark);
//                return bookmarkMapper.toBookmark(forumBookmark);
//            }
//        }
//
//        // Handle content bookmark if provided
//        if (bookmarkAddRequest.contentSlug() != null) {
//            Bookmark contentBookmark = bookmarkRepository.findByContentSlugAndAuthorUuid(bookmarkAddRequest.contentSlug(), authorUuid);
//            if (contentBookmark != null) {
//                if (contentBookmark.getIsBookmarked()) {
//                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This content is already bookmarked by the user.");
//                }
//                contentBookmark.setIsBookmarked(true);
//                contentBookmark.setIsDeleted(false);
//                bookmarkRepository.save(contentBookmark);
//                return bookmarkMapper.toBookmark(contentBookmark);
//            }
//        }
//
//        // If no existing bookmark is found, create a new one
//        Bookmark newBookmark = bookmarkMapper.fromBookmarkAddRequest(bookmarkAddRequest);
//        newBookmark.setAuthorUuid(authorUuid);
//        newBookmark.setCreatedAt(LocalDateTime.now());
//        newBookmark.setIsDeleted(false);
//        newBookmark.setIsBookmarked(true);
//        bookmarkRepository.save(newBookmark);
//        return bookmarkMapper.toBookmark(newBookmark);
//    }
    @Override
    public BookmarkResponse addBookmark(BookmarkAddRequest bookmarkAddRequest, String authorUuid) {
        Bookmark bookmark = null;
        if (bookmarkAddRequest.forumSlug() != null) {
            bookmark = bookmarkRepository.findByForumSlugAndAuthorUuid(bookmarkAddRequest.forumSlug(), authorUuid);
        } else if (bookmarkAddRequest.contentSlug() != null) {
            bookmark = bookmarkRepository.findByContentSlugAndAuthorUuid(bookmarkAddRequest.contentSlug(), authorUuid);
        }

        if (bookmark != null) {
            // Toggle the current bookmark state
            boolean newStatus = !bookmark.getIsBookmarked();
            bookmark.setIsBookmarked(newStatus);
            bookmark.setIsDeleted(!newStatus); // Mark as deleted when unbookmarked, active when bookmarked
            bookmarkRepository.save(bookmark);
            return bookmarkMapper.toBookmark(bookmark);
        } else if (bookmarkAddRequest.forumSlug() != null || bookmarkAddRequest.contentSlug() != null) {
            // Create a new bookmark
            Bookmark newBookmark = bookmarkMapper.fromBookmarkAddRequest(bookmarkAddRequest);
            newBookmark.setAuthorUuid(authorUuid);
            newBookmark.setCreatedAt(LocalDateTime.now());
            newBookmark.setIsDeleted(false);   // Active bookmark
            newBookmark.setIsBookmarked(true); // New bookmarks are active by default
            bookmarkRepository.save(newBookmark);
            return bookmarkMapper.toBookmark(newBookmark);
        }

        // If neither forumSlug nor contentSlug is provided, throw an error.
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No valid slug provided.");
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
