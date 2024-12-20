package istad.codeadvisor.userservice.feature.readingHistory;

import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryResponse;

import java.util.List;

public interface ReadingHistoryService {
    // retrieve all reading histories of a user
    List<ReadingHistoryResponse> getReadingHistories();
}
