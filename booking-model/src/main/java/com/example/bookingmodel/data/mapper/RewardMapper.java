package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.LevelDto;
import com.example.bookingmodel.data.dto.RewardDto;
import com.example.bookingmodel.data.entity.Level;
import com.example.bookingmodel.data.entity.Reward;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RewardMapper {

    private final ModelMapper modelMapper;

    public Reward mapToEntity(RewardDto dto) {
        return modelMapper.map(dto, Reward.class).toBuilder().build();
    }

    public RewardDto mapToDto(Reward entity) {
        return modelMapper.map(entity, RewardDto.class).toBuilder().build();
    }
}
