package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.LeveloverviewDto;
import com.example.bookingmodel.data.dto.WaitinglistoverviewDto;
import com.example.bookingmodel.data.entity.Leveloverview;
import com.example.bookingmodel.data.entity.Waitinglistoverview;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class WLOverviewMapper {

    private final ModelMapper modelMapper;

    public Waitinglistoverview mapToEntity(WaitinglistoverviewDto dto) {
        return modelMapper.map(dto, Waitinglistoverview.class).toBuilder().build();
    }

    public WaitinglistoverviewDto mapToDto(Waitinglistoverview entity) {
        return modelMapper.map(entity, WaitinglistoverviewDto.class).toBuilder().build();
    }
}