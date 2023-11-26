package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.CustomeroverviewDto;
import com.example.bookingmodel.data.dto.LeveloverviewDto;
import com.example.bookingmodel.data.entity.Customeroverview;
import com.example.bookingmodel.data.entity.Leveloverview;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LevelOverviewMapper {

    private final ModelMapper modelMapper;

    public Leveloverview mapToEntity(LeveloverviewDto dto) {
        return modelMapper.map(dto, Leveloverview.class).toBuilder().build();
    }

    public LeveloverviewDto mapToDto(Leveloverview entity) {
        return modelMapper.map(entity, LeveloverviewDto.class).toBuilder().build();
    }
}