package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.FavoriteDto;
import com.example.bookingmodel.data.dto.LogsHistoryDto;
import com.example.bookingmodel.data.entity.Favorite;
import com.example.bookingmodel.data.entity.LogsHistory;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LogsHistoryMapper {

    private final ModelMapper modelMapper;

    public LogsHistory mapToEntity(LogsHistoryDto dto) {
        return modelMapper.map(dto, LogsHistory.class).toBuilder().build();
    }

    public LogsHistoryDto mapToDto(LogsHistory entity) {
        return modelMapper.map(entity, LogsHistoryDto.class).toBuilder().build();
    }
}
