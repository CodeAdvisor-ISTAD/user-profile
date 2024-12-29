package istad.codeadvisor.userservice.feature.readingHistory;

import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryCreateRequest;
import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reading-histories")
public class ReadingHistoryController {
    private final ReadingHistoryService readingHistoryService;

    // post a reading history
    @PostMapping
    ReadingHistoryResponse createHistory(@RequestBody ReadingHistoryCreateRequest historyCreateRequest) {
        return readingHistoryService.createHistory(historyCreateRequest);
    }

    // retrieve all reading histories of a user
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
