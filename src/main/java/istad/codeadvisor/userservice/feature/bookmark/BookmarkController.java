package istad.codeadvisor.userservice.feature.bookmark;

import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkAddRequest;
import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    // add forum or content to bookmark
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    BookmarkResponse createForumBookmark(@RequestBody BookmarkAddRequest bookmarkAddRequest,
                                 @AuthenticationPrincipal Jwt jwt) {
        String authorUuid = jwt.getClaimAsString("userUuid");
        return bookmarkService.addBookmark(bookmarkAddRequest, authorUuid);
    }

    // get all bookmarks for a user
    @GetMapping
    Page<BookmarkResponse> getBookmarks(@AuthenticationPrincipal Jwt jwt,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        String authorUuid = jwt.getClaimAsString("userUuid");
        return bookmarkService.findAllBookmark(authorUuid, page, size);
    }

    @GetMapping("/check-bookmark")
    boolean checkBookmark(@RequestParam String forumSlug,
                          @AuthenticationPrincipal Jwt jwt) {
        String authorUuid = jwt.getClaimAsString("userUuid");
        return bookmarkService.isForumBookmarked(authorUuid,forumSlug);
    }

    // remove forum or content from bookmark
    @DeleteMapping("/{id}")
    void removeBookmark(@PathVariable String id) {
        bookmarkService.removeBookmark(id);
    }
}
