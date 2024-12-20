package istad.codeadvisor.userservice.feature.readingHistory;

import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reading-histories")
public class ReadingHistoryController {
    private final ReadingHistoryService readingHistoryService;

    // retrieve all reading histories of a user
    @GetMapping
    List<ReadingHistoryResponse> getReadingHistories() {
        return readingHistoryService.getReadingHistories();
    }
}
