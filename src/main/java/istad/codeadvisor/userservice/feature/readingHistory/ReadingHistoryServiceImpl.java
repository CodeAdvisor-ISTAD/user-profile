package istad.codeadvisor.userservice.feature.readingHistory;

import istad.codeadvisor.userservice.domain.ReadingHistory;
import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryCreateRequest;
import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryResponse;
import istad.codeadvisor.userservice.mapper.HistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadingHistoryServiceImpl implements ReadingHistoryService {
    private final ReadingHistoryRepository readingHistoryRepository;
    private final HistoryMapper historyMapper;

    // post a reading history
    @Override
    public ReadingHistoryResponse createHistory(ReadingHistoryCreateRequest historyCreateRequest) {
        // Check if a history record already exists and delete it if present
        Optional<ReadingHistory> existingHistory = readingHistoryRepository.findByUserIdAndContentIdAndQuestionId(
                historyCreateRequest.userId(),
                historyCreateRequest.contentId(),
                historyCreateRequest.questionId());
        existingHistory.ifPresent(readingHistoryRepository::delete);

        // Create and save the new history
        ReadingHistory readingHistory = historyMapper.fromReadingHistoryResponse(historyCreateRequest);
        readingHistory.setId(UUID.randomUUID().toString());
        readingHistory.setReadAt(LocalDateTime.now());
        readingHistoryRepository.save(readingHistory);

        // Return the newly created history as a response
        return historyMapper.toReadingHistory(readingHistory);
    }

    // retrieve all reading histories of a user
    @Override
    public List<ReadingHistoryResponse> getReadingHistories() {
        List<ReadingHistory> readingHistoryResponses = readingHistoryRepository.findAll();
        return historyMapper.toReadingHistoryResponseList(readingHistoryResponses);
    }

    // Disable a reading history
    @Override
    public void deleteReadingHistory(String id) {
        ReadingHistory readingHistory = readingHistoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reading history not found"));
        readingHistoryRepository.delete(readingHistory);

    }



}
