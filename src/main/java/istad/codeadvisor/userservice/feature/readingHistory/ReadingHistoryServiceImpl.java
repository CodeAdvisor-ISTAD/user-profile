package istad.codeadvisor.userservice.feature.readingHistory;

import istad.codeadvisor.userservice.domain.ReadingHistory;
import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryCreateRequest;
import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryResponse;
import istad.codeadvisor.userservice.mapper.HistoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
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
        // Check if this specific forum or content already exists in history
        ReadingHistory existingHistory = readingHistoryRepository.findByForumSlugOrContentSlugAndAuthorUuid(
                historyCreateRequest.forumSlug(),
                historyCreateRequest.contentSlug(),
                authorUuid
        );

        log.info("Existing history: {}", existingHistory);

        // If this specific history exists, just update timestamp
        if (existingHistory != null) {
            existingHistory.setCreatedAt(LocalDateTime.now());
            readingHistoryRepository.save(existingHistory);
            return historyMapper.toReadingHistory(existingHistory);
        }

        // If it's a new forum/content, create new history entry
        // (old entries remain in database)
        ReadingHistory readingHistory = historyMapper.fromReadingHistoryResponse(historyCreateRequest);
        readingHistory.setId(UUID.randomUUID().toString());
        readingHistory.setAuthorUuid(authorUuid);
        readingHistory.setCreatedAt(LocalDateTime.now());
        readingHistoryRepository.save(readingHistory);

        return historyMapper.toReadingHistory(readingHistory);
    }

    // retrieve all reading histories of a user
    @Override
    public List<ReadingHistoryResponse> getReadingHistories() {
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        List<ReadingHistory> readingHistoryResponses = readingHistoryRepository.findAll(sortByCreatedAtDesc);
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
