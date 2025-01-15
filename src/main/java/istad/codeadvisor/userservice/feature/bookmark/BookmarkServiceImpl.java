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
        // Check if the user has already bookmarked the same forum or content
        List<Bookmark> bookmarks = bookmarkRepository.findAllByAuthorUuidAndIsDeletedFalse(authorUuid);

        bookmarks.forEach(bookmark -> {
            if(bookmark.getForumUuid().equals(bookmarkAddRequest.forumUuid()) || bookmark.getContentUuid().equals(bookmarkAddRequest.contentUuid())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Already bookmarked this forum or content !"
                );
            }
        });

        // If no bookmark exists for the user, create a new one
        Bookmark newBookmark = bookmarkMapper.fromBookmarkAddRequest(bookmarkAddRequest);
        newBookmark.setAuthorUuid(authorUuid);
        newBookmark.setCreatedAt(LocalDateTime.now());
        newBookmark.setIsDeleted(false); // New bookmarks are active
        bookmarkRepository.save(newBookmark);
        return bookmarkMapper.toBookmark(newBookmark); // Return the newly created bookmark
    }

    @Override
    public Page<BookmarkResponse> findAllBookmark(String authorUuid, int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Bookmark> bookmarks = bookmarkRepository.findAllByAuthorUuidAndIsDeletedFalse(authorUuid, pageable);

        return bookmarks.map(bookmarkMapper::toBookmark);
    }



    // Get all bookmarks for a user
//    @Override
//    public List<BookmarkResponse> getBookmarks(String userId) {
//        List<Bookmark> bookmarks = Collections.singletonList(bookmarkRepository.findByUserId(userId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        "Do not have any bookmark !")));
//        return bookmarkMapper.toBookmarkList(bookmarks);
//    }

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
