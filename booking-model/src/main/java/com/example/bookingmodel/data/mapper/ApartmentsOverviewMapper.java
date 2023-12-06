package com.example.bookingmodel.data.mapper;

import com.example.bookingmodel.data.dto.ApartmentsoverviewDto;
import com.example.bookingmodel.data.dto.FavoriteDto;
import com.example.bookingmodel.data.entity.Apartmentsoverview;
import com.example.bookingmodel.data.entity.Favorite;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ApartmentsOverviewMapper {

    private final ModelMapper modelMapper;

    public Apartmentsoverview mapToEntity(ApartmentsoverviewDto dto) {
        return modelMapper.map(dto, Apartmentsoverview.class).toBuilder().build();
    }

    public ApartmentsoverviewDto mapToDto(Apartmentsoverview entity) {
        return modelMapper.map(entity, ApartmentsoverviewDto.class).toBuilder().build();
    }
}
