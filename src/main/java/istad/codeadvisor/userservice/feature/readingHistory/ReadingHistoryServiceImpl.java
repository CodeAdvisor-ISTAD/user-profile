package istad.codeadvisor.userservice.feature.readingHistory;

import istad.codeadvisor.userservice.domain.ReadingHistory;
import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryResponse;
import istad.codeadvisor.userservice.mapper.HistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingHistoryServiceImpl implements ReadingHistoryService {
    private final ReadingHistoryRepository readingHistoryRepository;
    private final HistoryMapper historyMapper;

    // retrieve all reading histories of a user
    @Override
    public List<ReadingHistoryResponse> getReadingHistories() {
        List<ReadingHistory> readingHistoryResponses = readingHistoryRepository.findAll();
        return historyMapper.toReadingHistoryResponseList(readingHistoryResponses);
    }
}
