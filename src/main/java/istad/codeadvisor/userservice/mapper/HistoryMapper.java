package istad.codeadvisor.userservice.mapper;

import istad.codeadvisor.userservice.domain.ReadingHistory;
import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryCreateRequest;
import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface HistoryMapper {
    ReadingHistory fromReadingHistoryResponse(ReadingHistoryCreateRequest readingHistoryResponse);
    List<ReadingHistoryResponse> toReadingHistoryResponseList(List<ReadingHistory> readingHistories);
    ReadingHistoryResponse toReadingHistory(ReadingHistory readingHistory);
}
