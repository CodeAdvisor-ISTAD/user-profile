package istad.codeadvisor.userservice.mapper;

import istad.codeadvisor.userservice.domain.ReadingHistory;
import istad.codeadvisor.userservice.feature.readingHistory.dto.ReadingHistoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface HistoryMapper {
    List<ReadingHistoryResponse> toReadingHistoryResponseList(List<ReadingHistory> readingHistories);
}
