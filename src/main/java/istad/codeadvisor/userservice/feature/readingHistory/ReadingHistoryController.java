package istad.codeadvisor.userservice.feature.readingHistory;

import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryCreateRequest;
import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/history")
public class ReadingHistoryController {
    private final ReadingHistoryService readingHistoryService;

    // post a reading history
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping
//    ReadingHistoryResponse createHistory(@RequestBody ReadingHistoryCreateRequest historyCreateRequest,
//                                         @AuthenticationPrincipal Jwt jwt) {
//        String authorUuid = jwt.getClaimAsString("userUuid");
//        return readingHistoryService.createHistory(historyCreateRequest, authorUuid);
//    }

    @PostMapping
    ReadingHistoryResponse createHistory(@RequestBody ReadingHistoryCreateRequest historyCreateRequest,
                                         @AuthenticationPrincipal Jwt jwt) {
        String authorUuid = jwt.getClaimAsString("userUuid");
        return readingHistoryService.createHistory(historyCreateRequest, authorUuid);
    }

    // retrieve all reading histories of a user
//    @PreAuthorize("isAuthenticated()")
    @GetMapping
    List<ReadingHistoryResponse> getReadingHistories() {
        return readingHistoryService.getReadingHistories();
    }

    // Disable a reading history
    @PatchMapping("/{id}/disable")
    void deleteReadingHistory(@PathVariable String id) {
        readingHistoryService.deleteReadingHistory(id);
    }
}
