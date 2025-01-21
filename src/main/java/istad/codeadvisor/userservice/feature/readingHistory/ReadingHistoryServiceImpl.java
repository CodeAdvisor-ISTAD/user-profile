package istad.codeadvisor.userservice.feature.readingHistory;

import istad.codeadvisor.userservice.domain.ReadingHistory;
import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryCreateRequest;
import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryResponse;
import istad.codeadvisor.userservice.mapper.HistoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReadingHistoryServiceImpl implements ReadingHistoryService {
    private final ReadingHistoryRepository readingHistoryRepository;
    private final HistoryMapper historyMapper;

    // post a reading history
    @Override
    public ReadingHistoryResponse createHistory(ReadingHistoryCreateRequest historyCreateRequest, String authorUuid) {
        // Check if a history record already exists for the forum or content slug
        ReadingHistory existingHistory = readingHistoryRepository.findByForumSlugOrContentSlug(
                historyCreateRequest.forumSlug(),
                historyCreateRequest.contentSlug()
        );

        log.info("Existing history: {}", existingHistory);

        // If a history record exists, update its createdAt time
        if (existingHistory != null) {
            existingHistory.setCreatedAt(LocalDateTime.now()); // Update the createdAt time
            readingHistoryRepository.save(existingHistory); // Save the updated entity
            return historyMapper.toReadingHistory(existingHistory); // Return the updated history
        }

        // If no history record exists, create a new one
        ReadingHistory readingHistory = historyMapper.fromReadingHistoryResponse(historyCreateRequest);
        readingHistory.setId(UUID.randomUUID().toString());
        readingHistory.setAuthorUuid(authorUuid);
        readingHistory.setCreatedAt(LocalDateTime.now());
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
