package istad.codeadvisor.userservice.feature.readingHistory;

import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryCreateRequest;
import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryResponse;

import java.util.List;

public interface ReadingHistoryService {
    // post a reading history
    ReadingHistoryResponse createHistory(ReadingHistoryCreateRequest historyCreateRequest, String authorUuid);
    // retrieve all reading histories of a user
    List<ReadingHistoryResponse> getReadingHistories();

    // Disable the reading history of a user
    void deleteReadingHistory(String id);
}
