package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.FavoriteDto;
import com.example.bookingmodel.data.dto.LevelDto;
import com.example.bookingmodel.data.entity.Favorite;
import com.example.bookingmodel.data.entity.Level;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LevelMapper {
    private final ModelMapper modelMapper;

    public Level mapToEntity(LevelDto dto) {
        return modelMapper.map(dto, Level.class).toBuilder().build();
    }

    public LevelDto mapToDto(Level entity) {
        return modelMapper.map(entity, LevelDto.class).toBuilder().build();
    }
}
