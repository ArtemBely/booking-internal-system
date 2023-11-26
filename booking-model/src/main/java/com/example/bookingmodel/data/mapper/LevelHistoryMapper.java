package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.CustomerLevelHistoryDto;
import com.example.bookingmodel.data.dto.CustomeroverviewDto;
import com.example.bookingmodel.data.entity.CustomerLevelHistory;
import com.example.bookingmodel.data.entity.Customeroverview;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class LevelHistoryMapper {

    private final ModelMapper modelMapper;

    public CustomerLevelHistory mapToEntity(CustomerLevelHistoryDto dto) {
        return modelMapper.map(dto, CustomerLevelHistory.class).toBuilder().build();
    }

    public CustomerLevelHistoryDto mapToDto(CustomerLevelHistory entity) {
        return modelMapper.map(entity, CustomerLevelHistoryDto.class).toBuilder().build();
    }
}