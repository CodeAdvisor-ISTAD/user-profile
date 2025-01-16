package istad.codeadvisor.userservice.feature.bookmark;

import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkAddRequest;
import istad.codeadvisor.userservice.feature.bookmark.dto.BookmarkResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    // add forum or content to bookmark
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    BookmarkResponse addBookmark(@RequestBody BookmarkAddRequest bookmarkAddRequest) {
        return bookmarkService.addBookmark(bookmarkAddRequest);
    }

    // get all bookmarks for a user
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    List<BookmarkResponse> getBookmarks(@AuthenticationPrincipal Jwt jwt) {
        String authorUuid = jwt.getClaimAsString("authorUuid");
        return bookmarkService.getBookmarks(authorUuid);
    }
    // remove forum or content from bookmark
    @DeleteMapping("/{id}")
    void removeBookmark(@PathVariable String id) {
        bookmarkService.removeBookmark(id);
    }
}
